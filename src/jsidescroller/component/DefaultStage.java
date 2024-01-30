package jsidescroller.component;

import java.awt.Color;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import jsidescroller.common.Chip;
import jsidescroller.common.Player;
import jsidescroller.common.Stage;

public class DefaultStage extends Stage {

  private int rows;
  private int columns;

  @Override
  public int rows() {
    return rows;
  }

  @Override
  public int columns() {
    return columns;
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
  protected Color backgroundColor() {
    return Color.BLACK;
  }

  @Override
  protected Map<Stage.Point, Chip> newStage() {
    final char[][] data = DefaultStageDataLoader.loadStageData();
    this.rows = data.length;
    this.columns = data[0].length;

    Map<Stage.Point, Chip> stage = new HashMap<>();
    for (int y = 0; y < rows(); y++) {
      for (int x = 0; x < columns(); x++) {
        java.awt.Point location = new java.awt.Point(x * chipSize(), y * chipSize());

        Chip chip =
            switch (data[y][x]) {
              case '0' -> new ColorChip(
                  this, chipSize(), location, backgroundColor(), Chip.Type.VOID);
              case '1' -> new ColorChip(this, chipSize(), location, Color.GRAY, Chip.Type.BLOCK);
              case '2' -> new DefaultCoin(this, chipSize(), location);
              case '3' -> new DefaultItemBlock(this, chipSize(), location);
              default -> throw new IllegalArgumentException("Unexpected value: " + data[y][x]);
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
