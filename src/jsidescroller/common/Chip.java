package jsidescroller.common;

import java.util.Objects;
import jsidescroller.common.interfaces.SideScrollerComponent;

public abstract class Chip implements SideScrollerComponent {

  public enum Type {
    PLAYER,
    BLOCK,
    COIN,
    VOID,
  }

  private final Stage stage;
  private final int size;

  public Chip(Stage stage, int size) {
    this.stage = Objects.requireNonNull(stage);
    if (size <= 0) {
      throw new IllegalArgumentException("size:" + size);
    }
    this.size = size;
  }

  @Override
  public Stage getStage() {
    return stage;
  }

  @Override
  public final int width() {
    return size;
  }

  @Override
  public final int height() {
    return size;
  }

  public abstract Type type();
}
