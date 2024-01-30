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

  public static char[][] loadStageData() {
    final Class<DefaultStageDataLoader> thisClass = DefaultStageDataLoader.class;
    final String name = "stage.dat";
    final Charset charset = StandardCharsets.UTF_8;

    try (InputStream stream = thisClass.getResourceAsStream(name);
        InputStreamReader reader = new InputStreamReader(stream, charset);
        BufferedReader file = new BufferedReader(reader)) {
      List<char[]> stageData =
          file.lines()
              .map(String::toCharArray)
              .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
      return checkStageData(stageData.toArray(new char[stageData.size()][]));
    } catch (IOException ioe) {
      throw new UncheckedIOException(ioe);
    }
  }

  private static char[][] checkStageData(char[][] stageData) {
    for (int i = 1; i < stageData.length; i++) {
      if (stageData[i].length != stageData[0].length) {
        throw new IllegalArgumentException(Arrays.toString(stageData));
      }
    }
    return stageData;
  }
}
