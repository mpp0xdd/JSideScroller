package jsidescroller.common;

import java.awt.Graphics;
import java.util.Map;
import java.util.Objects;

public abstract class Stage implements Drawable, Rectangular, Locatable, GravitationalField {

  public final class Point {

    public static Point of(Stage stage, int x, int y) {
      if (x < 0 || x >= stage.columns()) {
        throw new IllegalArgumentException("x:" + x);
      }
      if (y < 0 || y >= stage.rows()) {
        throw new IllegalArgumentException("y:" + y);
      }
      return stage.new Point(x, y);
    }

    private final int x;
    private final int y;

    private Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public int x() {
      return x;
    }

    public int y() {
      return y;
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) return true;
      if (obj == null) return false;
      if (getClass() != obj.getClass()) return false;
      Point other = (Point) obj;
      return x == other.x && y == other.y;
    }
  }

  private final Map<Stage.Point, Chip> stage;
  private final Player player;

  public Stage() {
    this.stage = newStage();
    this.player = newPlayer();
  }

  public abstract int rows();

  public abstract int columns();

  public abstract int chipSize();

  @Override
  public final int x() {
    return 0;
  }

  @Override
  public final int y() {
    return 0;
  }

  @Override
  public final java.awt.Point getLocation() {
    return new java.awt.Point(x(), y());
  }

  @Override
  public void provideGravity() {
    player().accept(this);
  }

  @Override
  public void draw(Graphics g) {
    stage().values().forEach(chip -> chip.draw(g));
    player().draw(g);
  }

  public Player player() {
    return player;
  }

  protected Map<Stage.Point, Chip> stage() {
    return stage;
  }

  protected abstract Map<Stage.Point, Chip> newStage();

  protected abstract Player newPlayer();
}
