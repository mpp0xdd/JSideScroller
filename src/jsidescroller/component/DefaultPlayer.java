package jsidescroller.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import jsidescroller.common.Direction;
import jsidescroller.common.Player;
import jsidescroller.common.Velocity;

public class DefaultPlayer implements Player {

  private final Point location = new Point(255, 255);
  private Velocity velocity = Velocity.ZERO;

  @Override
  public void draw(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    g2.setColor(Color.RED);
    g2.fill(asRectangle());
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
  public int speed() {
    return 10;
  }

  @Override
  public Velocity velocity() {
    return velocity;
  }

  @Override
  public void accelerate(Direction direction) {
    switch (direction) {
      case LEFT -> velocity = Velocity.of(-speed(), velocity.y());
      case RIGHT -> velocity = Velocity.of(speed(), velocity.y());
    }
  }

  @Override
  public void stop() {
    velocity = Velocity.ZERO;
  }

  @Override
  public void move() {
    location.translate(velocity().x(), velocity().y());
  }
}
