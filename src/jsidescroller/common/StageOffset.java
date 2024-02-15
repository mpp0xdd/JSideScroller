package jsidescroller.common;

import java.awt.Point;
import java.util.Objects;
import jsidescroller.common.interfaces.Immutable;
import jsidescroller.common.interfaces.SideScrollerComponent;

public final class StageOffset implements Immutable {

  public static StageOffset of(Stage stage) {
    int offsetWidth = stage.width() / 2 - stage.player().x();
    offsetWidth = Math.min(offsetWidth, 0);
    offsetWidth = Math.max(offsetWidth, stage.width() - stage.columns() * stage.chipSize());
    offsetWidth = Math.abs(offsetWidth);

    int offsetHeight = stage.height() / 2 - stage.player().y();
    offsetHeight = Math.min(offsetHeight, 0);
    offsetHeight = Math.max(offsetHeight, stage.height() - stage.rows() * stage.chipSize());
    offsetHeight = Math.abs(offsetHeight);

    return new StageOffset(offsetWidth, offsetHeight);
  }

  private final int width;
  private final int height;

  private StageOffset(int width, int height) {
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
    Stage stage = component.getStage();
    Point componentLocation = component.getLocation();
    componentLocation.translate(stage.x(), stage.y());
    componentLocation.translate(-width, -height);
    return componentLocation;
  }

  @Override
  public int hashCode() {
    return Objects.hash(height, width);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    StageOffset other = (StageOffset) obj;
    return height == other.height && width == other.width;
  }
}
