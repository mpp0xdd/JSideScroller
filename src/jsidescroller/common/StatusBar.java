package jsidescroller.common;

import java.awt.Point;
import java.util.Objects;
import jsidescroller.common.interfaces.Drawable;
import jsidescroller.common.interfaces.Locatable;
import jsidescroller.common.interfaces.Rectangular;

public abstract class StatusBar implements Drawable, Rectangular, Locatable {

  private Stage stage;

  public StatusBar() {}

  public final Stage getStage() {
    return stage;
  }

  public final void setStage(Stage stage) {
    this.stage = Objects.requireNonNull(stage);
  }

  @Override
  public final Point getLocation() {
    return new Point(x(), y());
  }

  @Override
  public final int x() {
    return stage.x();
  }

  @Override
  public final int y() {
    return stage.y() - this.height();
  }

  @Override
  public final int width() {
    return stage.width();
  }
}
