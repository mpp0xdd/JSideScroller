package jsidescroller.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Objects;
import jsidescroller.common.Enemy;
import jsidescroller.common.Stage;
import jsidescroller.common.interfaces.GravitationalField;

class DefaultEnemy extends Enemy {

  private final Point location;

  public DefaultEnemy(Stage stage, int size, Point location) {
    super(stage, size);
    this.location = Objects.requireNonNull(location).getLocation();
  }

  @Override
  public void move() {
    // nop
  }

  @Override
  public void accept(GravitationalField field) {
    // nop
  }

  @Override
  public void draw(Graphics g) {
    Dimension offset = getStage().calculateOffset(getStage().player());
    g.setColor(new Color(200, 76, 12));
    g.fill3DRect(x() - offset.width, y() - offset.height, width(), height(), true);
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
