package jsidescroller.common;

import jsidescroller.common.interfaces.SideScrollerComponent;

public abstract class Sprite implements SideScrollerComponent {

  private final Stage stage;
  private final int width;
  private final int height;

  public Sprite(Stage stage, int width, int height) {
    this.stage = stage;
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
