package jsidescroller.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Objects;
import jsidescroller.common.Chip;
import jsidescroller.common.Enemy;
import jsidescroller.common.Stage;
import jsidescroller.common.Velocity;
import jsidescroller.common.interfaces.GravitationalField;

class DefaultEnemy extends Enemy {

  public static DefaultEnemy of(Stage stage, int size, Point location) {
    return new DefaultEnemy(stage, size, location);
  }

  private final Point location;
  private Velocity velocity;
  private boolean isOnGround;

  private DefaultEnemy(Stage stage, int size, Point location) {
    super(stage, size);
    this.location = Objects.requireNonNull(location).getLocation();
    this.velocity = Velocity.of(2, 0);
    this.isOnGround = false;
  }

  @Override
  public boolean isAlive() {
    return true;
  }

  @Override
  public boolean isDead() {
    return false;
  }

  @Override
  public void die() {
    throw new RuntimeException("Not yet implemented.");
  }

  @Override
  public Velocity velocity() {
    return velocity;
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
    velocity = velocity().editor().addY(field.gravity()).edit();
  }

  @Override
  public void draw(Graphics g) {
    Dimension offset = getStage().calculateOffset(getStage().player());
    g.setColor(new Color(200, 76, 12));
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

  private void handleHorizontalMovement(Chip collidedChip) {
    if (velocity().isLeftward()) {
      location.x = collidedChip.x() + collidedChip.width() + 1;
    } else if (velocity().isRightward()) {
      location.x = collidedChip.x() - this.width() - 1;
    }
    velocity = velocity().editor().negateX().edit();
  }

  private void handleVerticalMovement(Chip collidedChip) {
    if (velocity().isUpward()) {
      location.y = collidedChip.y() + collidedChip.height() + 1;
    } else if (velocity().isDownward()) {
      location.y = collidedChip.y() - this.height() - 1;
      isOnGround = true;
    }
    velocity = velocity().editor().y(0).edit();
  }
}
