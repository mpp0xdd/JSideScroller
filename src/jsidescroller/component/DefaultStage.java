package jsidescroller.component;

import java.awt.Color;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import jsidescroller.common.Chip;
import jsidescroller.common.Player;
import jsidescroller.common.Stage;

public class DefaultStage extends Stage {

  @Override
  public int rows() {
    return 15;
  }

  @Override
  public int columns() {
    return 20;
  }

  @Override
  public int chipSize() {
    return 32;
  }

  @Override
  public int width() {
    return 640;
  }

  @Override
  public int height() {
    return 480;
  }

  @Override
  public int gravity() {
    return 1;
  }

  @Override
  protected Map<Stage.Point, Chip> newStage() {
    final int[][] data = {
      {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
      {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
      {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
      {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
      {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
      {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
      {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1},
      {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
      {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
      {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
      {1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
      {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
      {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
      {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
      {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    Map<Stage.Point, Chip> stage = new HashMap<>();
    for (int y = 0; y < rows(); y++) {
      for (int x = 0; x < columns(); x++) {
        java.awt.Point location = new java.awt.Point(x * chipSize(), y * chipSize());

        Chip chip =
            switch (data[y][x]) {
              case 0 -> new ColorChip(chipSize(), this, location, Color.BLACK, Chip.Type.VOID);
              case 1 -> new ColorChip(chipSize(), this, location, Color.GRAY, Chip.Type.BLOCK);
              default -> throw new IllegalArgumentException("Unexpected value: " + data[x][y]);
            };

        stage.put(Stage.Point.of(this, x, y), chip);
      }
    }

    return Collections.unmodifiableMap(stage);
  }

  @Override
  protected Player newPlayer() {
    return new DefaultPlayer(this);
  }
}
