package jsidescroller.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Objects;
import jsidescroller.common.Item;
import jsidescroller.common.Stage;
import jsidescroller.common.Stage.Offset;

class DefaultItem extends Item {

  public static DefaultItem of(Stage stage, int size, Point location) {
    return new DefaultItem(stage, size, location);
  }

  private final Point location;

  private DefaultItem(Stage stage, int size, Point location) {
    super(stage, size);
    this.location = Objects.requireNonNull(location).getLocation();
  }

  @Override
  public void draw(Graphics g) {
    if (isTaken()) return;

    Offset offset = Stage.Offset.of(getStage(), getStage().player());
    Point point = offset.apply(this);

    g.setColor(Color.GREEN);
    g.fillOval(point.x, point.y, width(), height());
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
}
