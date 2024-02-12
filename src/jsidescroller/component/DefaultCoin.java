package jsidescroller.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Objects;
import jsidescroller.common.Coin;
import jsidescroller.common.Stage;
import jsidescroller.common.StageOffset;

class DefaultCoin extends Coin {

  public static DefaultCoin of(Stage stage, int size, Point location) {
    return new DefaultCoin(stage, size, location);
  }

  private final Point location;

  private DefaultCoin(Stage stage, int size, Point location) {
    super(stage, size);
    this.location = Objects.requireNonNull(location).getLocation();
  }

  @Override
  public void draw(Graphics g) {
    if (isTaken()) return;

    StageOffset offset = StageOffset.of(getStage(), getStage().player());
    Point point = offset.apply(this);

    g.setColor(Color.YELLOW);
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
