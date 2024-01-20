package jsidescroller.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Objects;
import jsidescroller.common.Direction;
import jsidescroller.common.GravitationalField;
import jsidescroller.common.Player;
import jsidescroller.common.Stage;
import jsidescroller.common.Velocity;

class DefaultPlayer extends Player {

  private final Stage stage;
  private final Point location = new Point(255, 255);
  private Velocity velocity = Velocity.ZERO;
  private boolean isOnGround = true;

  public DefaultPlayer(Stage stage) {
    super(32);
    this.stage = Objects.requireNonNull(stage);
  }

  @Override
  public Stage getStage() {
    return stage;
  }

  @Override
  public void draw(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    g2.setColor(Color.RED);
    g2.fill(asRectangle());
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
      case LEFT -> velocity = Velocity.of(-speed(), velocity.y());
      case RIGHT -> velocity = Velocity.of(speed(), velocity.y());
    }
  }

  @Override
  public void stop() {
    velocity = Velocity.of(0, velocity.y());
  }

  @Override
  public int jumpSpeed() {
    return 18;
  }

  @Override
  public void jump() {
    if (isJumpable()) {
      velocity = Velocity.of(velocity.x(), -jumpSpeed());
      isOnGround = false;
    }
  }

  @Override
  public boolean isJumpable() {
    return isOnGround;
  }

  @Override
  public void move() {
    location.translate(velocity().x(), 0);
    getStage()
        .blockadeChip(this)
        .ifPresent(
            chip -> {
              if (velocity().isLeftward()) {
                location.x = chip.x() + chip.width() + 1;
              } else if (velocity().isRightward()) {
                location.x = chip.x() - this.width() - 1;
              }
              velocity = Velocity.of(0, velocity.y());
            });

    location.translate(0, velocity.y());
    getStage()
        .blockadeChip(this)
        .ifPresent(
            chip -> {
              if (velocity().isUpward()) {
                location.y = chip.y() + chip.height() + 1;
              } else if (velocity().isDownward()) {
                location.y = chip.y() - this.height() - 1;
                isOnGround = true;
              }
              velocity = Velocity.of(velocity.x(), 0);
            });
  }

  @Override
  public void accept(GravitationalField field) {
    if (isJumpable()) return;
    velocity = Velocity.of(velocity.x(), velocity.y() + field.gravity());
  }
}
