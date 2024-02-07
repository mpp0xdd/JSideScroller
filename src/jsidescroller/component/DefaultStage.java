package jsidescroller.component;

import java.awt.Color;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import jsidescroller.common.Chip;
import jsidescroller.common.Player;
import jsidescroller.common.Stage;

public class DefaultStage extends Stage {

  private static final char VOID = '0';
  private static final char BLOCK = '1';
  private static final char COIN = '2';
  private static final char ITEM_BLOCK = '3';
  private static final char ENEMY = '4';

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
              case VOID -> Void.of(this, location);
              case BLOCK -> ColorBlock.of(this, chipSize(), location, Color.GRAY);
              case COIN -> {
                add(DefaultCoin.of(this, chipSize(), location));
                yield Void.of(this, location);
              }
              case ITEM_BLOCK -> DefaultItemBlock.of(this, chipSize(), location);
              case ENEMY -> {
                add(DefaultEnemy.of(this, chipSize(), location));
                yield Void.of(this, location);
              }
              default -> throw new IllegalArgumentException("Unexpected value: " + data[y][x]);
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
