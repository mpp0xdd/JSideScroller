package jsidescroller.common;

import java.awt.Point;
import java.util.List;

public abstract class Stage implements Drawable, Rectangular, Locatable, GravitationalField {

  private final List<Chip> stage;
  private final Player player;

  public Stage() {
    this.stage = newStage();
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

  protected List<Chip> stage() {
    return stage;
  }

  protected abstract List<Chip> newStage();

  protected abstract Player newPlayer();
}
