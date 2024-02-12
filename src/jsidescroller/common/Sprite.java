package jsidescroller.common;

import java.util.Objects;
import jsidescroller.common.interfaces.SideScrollerComponent;

public abstract class Sprite implements SideScrollerComponent {

  private final Stage stage;
  private final int width;
  private final int height;

  public Sprite(Stage stage, int width, int height) {
    this.stage = Objects.requireNonNull(stage);
    if (width < 0) {
      throw new IllegalArgumentException("width:" + width);
    }
    if (height < 0) {
      throw new IllegalArgumentException("height:" + width);
    }

    this.width = width;
    this.height = height;
  }

  @Override
  public final Stage getStage() {
    return stage;
  }

  @Override
  public final int width() {
    return width;
  }

  @Override
  public final int height() {
    return height;
  }
}
