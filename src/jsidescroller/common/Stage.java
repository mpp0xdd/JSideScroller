package jsidescroller.common;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;
import jsidescroller.common.interfaces.Drawable;
import jsidescroller.common.interfaces.GravitationalField;
import jsidescroller.common.interfaces.Locatable;
import jsidescroller.common.interfaces.Rectangular;
import jsidescroller.common.interfaces.SideScrollerComponent;

public abstract class Stage implements Drawable, Rectangular, Locatable, GravitationalField {

  private final Map<StagePoint, Chip> stage;
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

  public abstract Color backgroundColor();

  public abstract Point playerStartingLocation();

  // TODO: When component.width() and this.chipSize() are not equal
  //        (component.height() and this.chipSize() are not equal).
  public Optional<Chip> blockadeChip(SideScrollerComponent component) {
    Point componentLocation = component.getLocation();

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
  public final Point getLocation() {
    return new Point(x(), y());
  }

  @Override
  public void provideGravity() {
    player().accept(this);
    enemies().forEach(e -> e.accept(this));
  }

  @Override
  public void draw(Graphics g) {
    Viewport viewport = new Viewport();

    drawStage(g, viewport);

    drawSprites(g, viewport, coins);
    drawSprites(g, viewport, items);
    drawSprites(g, viewport, enemies);
    drawSprite(g, viewport, player);
  }

  public Player player() {
    return player;
  }

  protected Map<StagePoint, Chip> stage() {
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

  protected abstract Map<StagePoint, Chip> newStage();

  protected abstract Player newPlayer();

  private Optional<StagePoint> toStagePoint(Point location) {
    try {
      int sx = location.x / chipSize();
      int sy = location.y / chipSize();
      return Optional.of(StagePoint.of(this, sx, sy));
    } catch (Exception e) {
      return Optional.empty();
    }
  }

  private Optional<Chip> existsBlockadeChip(Point location) {
    return toStagePoint(location).map(stage()::get).filter(this::isBlockadeChip);
  }

  private void drawStage(Graphics g, Viewport viewport) {
    viewport.stagePointStream().map(stage()::get).forEach(chip -> chip.draw(g));
  }

  private void drawSprites(Graphics g, Viewport viewport, List<? extends Sprite> sprites) {
    sprites.stream().filter(viewport::intersects).forEach(s -> s.draw(g));
  }

  private void drawSprite(Graphics g, Viewport viewport, Sprite sprite) {
    if (sprite.intersects(viewport)) {
      sprite.draw(g);
    }
  }

  private final class Viewport implements Rectangular, Locatable {

    private final int x;
    private final int y;

    public Viewport() {
      StageOffset offset = StageOffset.of(Stage.this);
      this.x = offset.width();
      this.y = offset.height();
    }

    public StagePoint first() {
      Point location = getLocation();
      return toStagePoint(location).orElseThrow();
    }

    public StagePoint last() {
      Point location = new Point(x() + width() - 1, y() + height() - 1);
      return toStagePoint(location).orElseThrow();
    }

    public Stream<StagePoint> stagePointStream() {
      Stream.Builder<StagePoint> builder = Stream.builder();

      StagePoint first = first();
      StagePoint last = last();
      for (int y = first.y(); y <= last.y(); y++) {
        for (int x = first.x(); x <= last.x(); x++) {
          builder.add(StagePoint.of(Stage.this, x, y));
        }
      }

      return builder.build();
    }

    @Override
    public int x() {
      return x;
    }

    @Override
    public int y() {
      return y;
    }

    @Override
    public Point getLocation() {
      return new Point(x(), y());
    }

    @Override
    public int width() {
      return Stage.this.width();
    }

    @Override
    public int height() {
      return Stage.this.height();
    }
  }
}
