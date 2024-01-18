package jsidescroller.component;

import java.awt.Graphics;
import java.awt.Point;
import jsidescroller.common.Player;
import jsidescroller.common.Stage;

public class DefaultStage extends Stage {

  @Override
  public void draw(Graphics g) {
    player().draw(g);
  }

  @Override
  public final int x() {
    return 0;
  }

  @Override
  public final int y() {
    return 0;
  }

  @Override
  public int width() {
    return 640;
  }

  @Override
  public int height() {
    return 480;
  }

  @Override
  public Point getLocation() {
    return new Point(x(), y());
  }

  @Override
  public int gravity() {
    return 1;
  }

  @Override
  public void provideGravity() {
    player().accept(this);
  }

  @Override
  protected Player newPlayer() {
    return new DefaultPlayer(this);
  }
}
