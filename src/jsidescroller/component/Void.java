package jsidescroller.component;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Objects;
import jsidescroller.common.Chip;
import jsidescroller.common.Stage;
import jsidescroller.common.interfaces.Rectangular;

class Void extends Chip {

  public static Void of(Stage stage, int size, Point location) {
    return new Void(stage, size, location);
  }

  private final Point location;

  private Void(Stage stage, int size, Point location) {
    super(stage, size);
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

  @Override
  public Rectangle asRectangle() {
    return new Rectangle(x(), y(), 0, 0);
  }

  @Override
  public boolean intersects(Rectangular other) {
    return false;
  }
}
