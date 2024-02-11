package jsidescroller.common;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import jsidescroller.common.interfaces.Drawable;
import jsidescroller.common.interfaces.GravitationalField;
import jsidescroller.common.interfaces.Immutable;
import jsidescroller.common.interfaces.Locatable;
import jsidescroller.common.interfaces.Rectangular;
import jsidescroller.common.interfaces.SideScrollerComponent;

public abstract class Stage implements Drawable, Rectangular, Locatable, GravitationalField {

  public final class Point implements Immutable {

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
  private final List<Coin> coins;
  private final List<Item> items;
  private final List<Enemy> enemies;

  private final Player player;

  public Stage() {
    this.coins = new ArrayList<>();
    this.items = new ArrayList<>();
    this.enemies = new ArrayList<>();

    this.stage = newStage();
    this.player = newPlayer();
  }

  public abstract int rows();

  public abstract int columns();

  public abstract int chipSize();

  public abstract java.awt.Point playerStartingLocation();

  public Optional<Chip> blockadeChip(SideScrollerComponent component) {
    java.awt.Point componentLocation = component.getLocation();

    // upper left corner
    Optional<Chip> result = existsBlockadeChip(componentLocation);
    if (result.isPresent()) {
      return result;
    }

    // lower left corner
    componentLocation.translate(0, component.height());
    result = existsBlockadeChip(componentLocation);
    if (result.isPresent()) {
      return result;
    }

    // upper right corner
    componentLocation.translate(component.width(), -component.height());
    result = existsBlockadeChip(componentLocation);
    if (result.isPresent()) {
      return result;
    }

    // lower right corner
    componentLocation.translate(0, component.height());
    result = existsBlockadeChip(componentLocation);
    return result;
  }

  public Dimension calculateOffset(SideScrollerComponent component) {
    int offsetWidth = width() / 2 - component.x();
    offsetWidth = Math.min(offsetWidth, 0);
    offsetWidth = Math.max(offsetWidth, width() - columns() * chipSize());
    offsetWidth = Math.abs(offsetWidth);

    int offsetHeight = height() / 2 - component.y();
    offsetHeight = Math.min(offsetHeight, 0);
    offsetHeight = Math.max(offsetHeight, height() - rows() * chipSize());
    offsetHeight = Math.abs(offsetHeight);

    return new Dimension(offsetWidth, offsetHeight);
  }

  public void add(Coin coin) {
    coins.add(coin);
  }

  public void add(Item item) {
    items.add(item);
  }

  public void add(Enemy enemy) {
    enemies.add(enemy);
  }

  public void remove(Coin coin) {
    coins.remove(coin);
  }

  public void remove(Item item) {
    items.remove(item);
  }

  public void remove(Enemy enemy) {
    enemies.remove(enemy);
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
    enemies().forEach(e -> e.accept(this));
  }

  @Override
  public void draw(Graphics g) {
    g.setColor(this.backgroundColor());
    g.fillRect(x(), y(), width(), height());

    Dimension offset = calculateOffset(player());
    java.awt.Point cursor = new java.awt.Point(offset.width, offset.height);
    Stage.Point start = toStagePoint(cursor).orElseThrow();
    cursor.translate(width() - 1, height() - 1);
    Stage.Point end = toStagePoint(cursor).orElseThrow();

    for (int y = start.y(); y <= end.y(); y++) {
      for (int x = start.x(); x <= end.x(); x++) {
        Stage.Point sp = Stage.Point.of(this, x, y);
        stage().get(sp).draw(g);
      }
    }
    coins.forEach(c -> c.draw(g));
    items.forEach(i -> i.draw(g));
    enemies.forEach(e -> e.draw(g));

    player().draw(g);
  }

  public Player player() {
    return player;
  }

  protected Map<Stage.Point, Chip> stage() {
    return stage;
  }

  public List<Coin> coins() {
    return Collections.unmodifiableList(coins);
  }

  public List<Item> items() {
    return Collections.unmodifiableList(items);
  }

  public List<Enemy> enemies() {
    return Collections.unmodifiableList(enemies);
  }

  protected boolean isBlockadeChip(Chip chip) {
    return chip.isBlock() || chip.isItemBlock();
  }

  protected abstract Color backgroundColor();

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
