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

class StageDataLoader {

  private StageDataLoader() {
    // restrict instantiation
  }

  public static int[][] loadStageData() {
    final Class<StageDataLoader> thisClass = StageDataLoader.class;
    final String name = "stage.dat";
    final Charset charset = StandardCharsets.UTF_8;

    try (InputStream stream = thisClass.getResourceAsStream(name);
        InputStreamReader reader = new InputStreamReader(stream, charset);
        BufferedReader file = new BufferedReader(reader)) {
      List<int[]> stageData =
          file.lines()
              .map(StageDataLoader::toIntArray)
              .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
      return checkStageData(stageData.toArray(new int[stageData.size()][]));
    } catch (IOException ioe) {
      throw new UncheckedIOException(ioe);
    }
  }

  private static int[][] checkStageData(int[][] stageData) {
    for (int i = 1; i < stageData.length; i++) {
      if (stageData[i].length != stageData[0].length) {
        throw new IllegalArgumentException(Arrays.toString(stageData));
      }
    }
    return stageData;
  }

  private static int toDigit(int codePoint) {
    return Character.digit(codePoint, 10);
  }

  private static int[] toIntArray(String line) {
    return line.chars().map(StageDataLoader::toDigit).toArray();
  }
}
