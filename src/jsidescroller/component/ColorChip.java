package jsidescroller.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Objects;
import jsidescroller.common.Chip;
import jsidescroller.common.ChipType;
import jsidescroller.common.Stage;

public class ColorChip extends Chip {

  private final Stage stage;
  private final Point location;
  private final Color color;
  private final ChipType chipType;

  public ColorChip(int size, Stage stage, Point location, Color color, ChipType chipType) {
    super(size);
    this.stage = Objects.requireNonNull(stage);
    this.location = Objects.requireNonNull(location);
    this.color = Objects.requireNonNull(color);
    this.chipType = Objects.requireNonNull(chipType);
  }

  @Override
  public ChipType chipType() {
    return chipType;
  }

  @Override
  public Stage getStage() {
    return stage;
  }

  @Override
  public void draw(Graphics g) {
    g.setColor(color);
    g.fill3DRect(x(), y(), width(), height(), true);
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
