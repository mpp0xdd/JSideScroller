package jsidescroller.component;

import java.awt.Graphics;
import java.awt.Point;
import java.util.Objects;
import jsidescroller.common.Chip;
import jsidescroller.common.Stage;
import jsidescroller.common.Stage.Offset;

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
    Offset offset = Stage.Offset.of(getStage(), getStage().player());
    Point point = offset.apply(this);

    g.setColor(getStage().backgroundColor());
    g.fill3DRect(point.x, point.y, width(), height(), true);
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
