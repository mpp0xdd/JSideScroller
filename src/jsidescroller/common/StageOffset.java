package jsidescroller.common;

import java.awt.Point;
import jsidescroller.common.interfaces.SideScrollerComponent;

public final class StageOffset {

  public static StageOffset of(Stage stage) {
    int offsetWidth = stage.width() / 2 - stage.player().x();
    offsetWidth = Math.min(offsetWidth, 0);
    offsetWidth = Math.max(offsetWidth, stage.width() - stage.columns() * stage.chipSize());
    offsetWidth = Math.abs(offsetWidth);

    int offsetHeight = stage.height() / 2 - stage.player().y();
    offsetHeight = Math.min(offsetHeight, 0);
    offsetHeight = Math.max(offsetHeight, stage.height() - stage.rows() * stage.chipSize());
    offsetHeight = Math.abs(offsetHeight);

    return new StageOffset(stage, offsetWidth, offsetHeight);
  }

  private final Stage stage;
  private final int width;
  private final int height;

  private StageOffset(Stage stage, int width, int height) {
    this.stage = stage;
    this.width = width;
    this.height = height;
  }

  public int width() {
    return width;
  }

  public int height() {
    return height;
  }

  public Point apply(SideScrollerComponent component) {
    Point componentLocation = component.getLocation();
    componentLocation.translate(stage.x(), stage.y());
    componentLocation.translate(-width, -height);
    return componentLocation;
  }
}
