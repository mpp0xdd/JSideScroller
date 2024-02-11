package jsidescroller.component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class DefaultStageDataLoader {

  private DefaultStageDataLoader() {
    // restrict instantiation
  }

  public static DefaultStageData[][] loadStageData() {
    final Class<DefaultStageDataLoader> thisClass = DefaultStageDataLoader.class;
    final String name = "stage.dat";
    final Charset charset = StandardCharsets.UTF_8;

    try (InputStream stream = thisClass.getResourceAsStream(name);
        InputStreamReader reader = new InputStreamReader(stream, charset);
        BufferedReader file = new BufferedReader(reader)) {
      List<DefaultStageData[]> stageData =
          file.lines()
              .map(DefaultStageDataLoader::toDefaultStageDataArray)
              .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
      return checkStageData(stageData.toArray(new DefaultStageData[stageData.size()][]));
    } catch (IOException ioe) {
      throw new UncheckedIOException(ioe);
    }
  }

  private static DefaultStageData[] toDefaultStageDataArray(String line) {
    char[] charArray = line.toCharArray();
    DefaultStageData[] stageDataArray = new DefaultStageData[charArray.length];
    for (int i = 0; i < stageDataArray.length; i++) {
      stageDataArray[i] = DefaultStageData.of(charArray[i]);
    }
    return stageDataArray;
  }

  private static DefaultStageData[][] checkStageData(DefaultStageData[][] stageData) {
    for (int i = 1; i < stageData.length; i++) {
      if (stageData[i].length != stageData[0].length) {
        throw new IllegalArgumentException(Arrays.toString(stageData));
      }
    }
    return stageData;
  }
}
