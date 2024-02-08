package jsidescroller.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;
import jsidescroller.common.Chip;
import jsidescroller.common.Direction;
import jsidescroller.common.Enemy;
import jsidescroller.common.ItemBlock;
import jsidescroller.common.Player;
import jsidescroller.common.Stage;
import jsidescroller.common.Velocity;
import jsidescroller.common.interfaces.GravitationalField;

class DefaultPlayer extends Player {

  public static DefaultPlayer of(Stage stage) {
    return new DefaultPlayer(stage);
  }

  private final Point location = new Point(255, 255);
  private Velocity velocity = Velocity.ZERO;
  private boolean isOnGround = false;

  private DefaultPlayer(Stage stage) {
    super(stage, 32);
  }

  @Override
  public void defeatEnemies() {
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
    Dimension offset = getStage().calculateOffset(this);
    g.setColor(Color.RED);
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
      case LEFT -> velocity = velocity.editor().x(-speed()).edit();
      case RIGHT -> velocity = velocity.editor().x(speed()).edit();
    }
  }

  @Override
  public void stop() {
    velocity = velocity.editor().x(0).edit();
  }

  @Override
  public int jumpSpeed() {
    return 18;
  }

  @Override
  public void jump() {
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
    if (velocity().isZero()) {
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
