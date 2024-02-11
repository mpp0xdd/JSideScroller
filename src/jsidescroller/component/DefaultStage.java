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
  public java.awt.Point playerStartingLocation() {
    return new java.awt.Point(255, 255);
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
    final DefaultStageData[][] data = DefaultStageDataLoader.loadStageData();
    this.rows = data.length;
    this.columns = data[0].length;

    Map<Stage.Point, Chip> stage = new HashMap<>();
    for (int y = 0; y < rows(); y++) {
      for (int x = 0; x < columns(); x++) {
        java.awt.Point location = new java.awt.Point(x * chipSize(), y * chipSize());

        Chip chip =
            switch (data[y][x]) {
              case VOID -> {
                yield Void.of(this, location);
              }
              case BLOCK -> {
                yield ColorBlock.of(this, chipSize(), location, Color.GRAY);
              }
              case COIN -> {
                add(DefaultCoin.of(this, chipSize(), location));
                yield Void.of(this, location);
              }
              case ITEM_BLOCK -> {
                yield DefaultItemBlock.of(this, chipSize(), location);
              }
              case ENEMY -> {
                add(DefaultEnemy.of(this, chipSize(), location));
                yield Void.of(this, location);
              }
              default -> {
                throw new IllegalArgumentException("Unexpected value: " + data[y][x]);
              }
            };

        stage.put(Stage.Point.of(this, x, y), chip);
      }
    }

    return Collections.unmodifiableMap(stage);
  }

  @Override
  protected Player newPlayer() {
    return DefaultPlayer.of(this);
  }
}
