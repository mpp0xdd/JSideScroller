package jsidescroller.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Objects;
import jsidescroller.common.Coin;
import jsidescroller.common.Stage;

class DefaultCoin extends Coin {

  private final Point location;

  public DefaultCoin(Stage stage, int size, Point location) {
    super(stage, size);
    this.location = Objects.requireNonNull(location).getLocation();
  }

  @Override
  public void draw(Graphics g) {
    if (isTaken()) return;

    Dimension offset = getStage().calculateOffset(getStage().player());
    g.setColor(Color.YELLOW);
    g.fillOval(x() - offset.width, y() - offset.height, width(), height());
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
