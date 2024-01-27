package jsidescroller.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Objects;
import jsidescroller.common.Chip;
import jsidescroller.common.Stage;

class ColorChip extends Chip {

  private final Point location;
  private final Color color;
  private final Chip.Type type;

  public ColorChip(Stage stage, int size, Point location, Color color, Chip.Type type) {
    super(stage, size);
    this.location = Objects.requireNonNull(location).getLocation();
    this.color = Objects.requireNonNull(color);
    this.type = Objects.requireNonNull(type);
  }

  @Override
  public Chip.Type type() {
    return type;
  }

  @Override
  public void draw(Graphics g) {
    Dimension offset = getStage().calculateOffset(getStage().player());
    g.setColor(color);
    if (isVoid()) {
      g.fillRect(x() - offset.width, y() - offset.height, width(), height());
    } else {
      g.fill3DRect(x() - offset.width, y() - offset.height, width(), height(), true);
    }
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
}
