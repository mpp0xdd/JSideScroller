package jsidescroller.common;

import java.awt.Graphics;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import jsidescroller.common.interfaces.Drawable;
import jsidescroller.common.interfaces.GravitationalField;
import jsidescroller.common.interfaces.Locatable;
import jsidescroller.common.interfaces.Rectangular;

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

  public Optional<Chip> blockadeChip(Chip chip) {
    java.awt.Point chipLocation = chip.getLocation();

    // upper left corner
    Optional<Chip> result = existsBlockadeChip(chipLocation);
    if (result.isPresent()) {
      return result;
    }

    // lower left corner
    chipLocation.translate(0, chip.height());
    result = existsBlockadeChip(chipLocation);
    if (result.isPresent()) {
      return result;
    }

    // upper right corner
    chipLocation.translate(chip.width(), -chip.height());
    result = existsBlockadeChip(chipLocation);
    if (result.isPresent()) {
      return result;
    }

    // lower right corner
    chipLocation.translate(0, chip.height());
    result = existsBlockadeChip(chipLocation);
    return result;
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

  protected boolean isBlockadeChip(Chip chip) {
    return chip.isBlock();
  }

  protected abstract Map<Stage.Point, Chip> newStage();

  protected abstract Player newPlayer();

  private Optional<Stage.Point> toStagePoint(java.awt.Point location) {
    try {
      int sx = location.x / chipSize();
      int sy = location.y / chipSize();
      return Optional.of(Stage.Point.of(this, sx, sy));
    } catch (Exception e) {
      return Optional.empty();
    }
  }

  private Optional<Chip> existsBlockadeChip(java.awt.Point location) {
    return toStagePoint(location).map(stage()::get).filter(this::isBlockadeChip);
  }
}
