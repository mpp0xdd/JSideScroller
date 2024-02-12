package jsidescroller.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.time.LocalDateTime;
import java.util.Objects;
import jsidescroller.common.Chip;
import jsidescroller.common.Enemy;
import jsidescroller.common.Stage;
import jsidescroller.common.StageOffset;
import jsidescroller.common.Velocity;
import jsidescroller.common.interfaces.GravitationalField;

class DefaultEnemy extends Enemy {

  public static DefaultEnemy of(Stage stage, Point location) {
    return new DefaultEnemy(stage, location);
  }

  private final Point location;
  private Velocity velocity;
  private boolean isOnGround;
  private boolean isAlive;

  private DefaultEnemy(Stage stage, Point location) {
    super(stage);
    this.location = Objects.requireNonNull(location).getLocation();
    this.velocity = Velocity.of(2, 0);
    this.isOnGround = false;
    this.isAlive = true;
  }

  @Override
  public void attack() {
    if (isDead()) return;

    if (this.intersects(getStage().player())) {
      System.err.println("Enemy attack: " + LocalDateTime.now());
    }
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
  public Velocity velocity() {
    return velocity;
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
    velocity = velocity().editor().addY(field.gravity()).edit();
  }

  @Override
  public void draw(Graphics g) {
    if (isDead()) return;

    StageOffset offset = StageOffset.of(getStage(), getStage().player());
    Point point = offset.apply(this);

    g.setColor(new Color(200, 76, 12));
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
