package jsidescroller.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;
import jsidescroller.common.Chip;
import jsidescroller.common.Coin;
import jsidescroller.common.Direction;
import jsidescroller.common.Enemy;
import jsidescroller.common.Item;
import jsidescroller.common.ItemBlock;
import jsidescroller.common.Player;
import jsidescroller.common.Stage;
import jsidescroller.common.StageOffset;
import jsidescroller.common.Velocity;
import jsidescroller.common.interfaces.GravitationalField;

class DefaultPlayer extends Player {

  public static DefaultPlayer of(Stage stage, int width, int height) {
    return new DefaultPlayer(stage, width, height);
  }

  private final Point location;
  private Velocity velocity;
  private boolean isOnGround;
  private boolean isAlive;

  private DefaultPlayer(Stage stage, int width, int height) {
    super(stage, width, height);
    this.location = stage.playerStartingLocation();
    this.velocity = Velocity.ZERO;
    this.isOnGround = false;
    this.isAlive = true;
  }

  @Override
  public boolean isAlive() {
    return isAlive;
  }

  @Override
  public boolean isDead() {
    return !isAlive;
  }

  @Override
  public void die() {
    if (isDead()) {
      throw new IllegalStateException("Already dead.");
    }
    this.isAlive = false;
  }

  @Override
  public void collectCoins() {
    if (isDead()) return;

    List<Coin> coins =
        getStage().coins().stream() //
            .filter(this::intersects)
            .filter(Coin::isNotTaken)
            .toList();

    coins.forEach(
        coin -> {
          coin.take();
          getStage().remove(coin);
        });

    getStage().coinCounter().add(coins.size());
  }

  @Override
  public void collectItems() {
    if (isDead()) return;

    List<Item> items =
        getStage().items().stream() //
            .filter(this::intersects)
            .filter(Item::isNotTaken)
            .toList();

    items.forEach(
        item -> {
          item.take();
          getStage().remove(item);
        });
  }

  @Override
  public void defeatEnemies() {
    if (isDead()) return;

    if (velocity().isDownward()) {
      List<Enemy> enemies =
          getStage().enemies().stream()
              .filter(this::intersects)
              .filter(enemy -> enemy.y() > this.y())
              .toList();

      if (!enemies.isEmpty()) {
        velocity = velocity.editor().y(-jumpSpeed() / 2).edit();
        isOnGround = true;
        enemies.forEach(
            enemy -> {
              enemy.die();
              getStage().remove(enemy);
            });
      }
    }
  }

  @Override
  public void draw(Graphics g) {
    if (isDead()) return;

    StageOffset offset = StageOffset.of(getStage());
    Point point = offset.apply(this);

    g.setColor(Color.RED);
    g.fill3DRect(point.x, point.y, width(), height(), true);
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
    if (isDead()) return;

    velocity =
        switch (direction) {
          case LEFT -> velocity.editor().x(-speed()).edit();
          case RIGHT -> velocity.editor().x(speed()).edit();
        };
  }

  @Override
  public void stop() {
    if (isDead()) return;

    velocity = velocity.editor().x(0).edit();
  }

  @Override
  public int jumpSpeed() {
    return 18;
  }

  @Override
  public void jump() {
    if (isDead()) return;

    if (isJumpable()) {
      velocity = velocity.editor().y(-jumpSpeed()).edit();
      isOnGround = false;
    }
  }

  @Override
  public boolean isJumpable() {
    return isOnGround;
  }

  @Override
  public void move() {
    if (isDead() || velocity().isZero()) {
      return;
    }

    location.translate(velocity().x(), 0);
    getStage().blockadeChip(this).ifPresent(this::handleHorizontalMovement);

    location.translate(0, velocity().y());
    getStage()
        .blockadeChip(this)
        .ifPresentOrElse(this::handleVerticalMovement, () -> isOnGround = false);
  }

  @Override
  public void accept(GravitationalField field) {
    if (isOnGround) return;
    velocity = velocity.editor().addY(field.gravity()).edit();
  }

  private void handleHorizontalMovement(Chip collidedChip) {
    if (velocity().isLeftward()) {
      location.x = collidedChip.x() + collidedChip.width() + 1;
    } else if (velocity().isRightward()) {
      location.x = collidedChip.x() - this.width() - 1;
    }
    stop();
  }

  private void handleVerticalMovement(Chip collidedChip) {
    if (velocity().isUpward()) {
      location.y = collidedChip.y() + collidedChip.height() + 1;
      if (collidedChip.isItemBlock()) {
        ItemBlock itemBlock = ItemBlock.class.cast(collidedChip);
        itemBlock.hit();
      }
    } else if (velocity().isDownward()) {
      location.y = collidedChip.y() - this.height() - 1;
      isOnGround = true;
    }
    velocity = velocity.editor().y(0).edit();
  }
}
