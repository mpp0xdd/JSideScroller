package jsidescroller.component;

import java.awt.Graphics;
import java.awt.Point;
import java.util.Objects;
import jsidescroller.common.Chip;
import jsidescroller.common.Stage;

class Void extends Chip {

  public static Void of(Stage stage, Point location) {
    return new Void(stage, location);
  }

  private final Point location;

  private Void(Stage stage, Point location) {
    super(stage, 0);
    this.location = Objects.requireNonNull(location).getLocation();
  }

  @Override
  public void draw(Graphics g) {
    // nop
  }

  @Override
  public int x() {
    return location.x;
  }

  @Override
  public int y() {
    return location.y;
  }

  @Override
  public Point getLocation() {
    return location.getLocation();
  }

  @Override
  public Type type() {
    return Type.VOID;
  }
}
