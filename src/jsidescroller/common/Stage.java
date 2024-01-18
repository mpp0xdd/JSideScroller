package jsidescroller.common;

import java.awt.Point;

public abstract class Stage implements Drawable, Rectangular, Locatable, GravitationalField {

  private final Player player;

  public Stage() {
    this.player = newPlayer();
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
  public final Point getLocation() {
    return new Point(x(), y());
  }

  public Player player() {
    return player;
  }

  protected abstract Player newPlayer();
}
