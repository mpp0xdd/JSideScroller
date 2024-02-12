package jsidescroller.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Objects;
import jsidescroller.common.Block;
import jsidescroller.common.Stage;
import jsidescroller.common.Stage.Offset;

class ColorBlock extends Block {

  public static ColorBlock of(Stage stage, int size, Point location, Color color) {
    return new ColorBlock(stage, size, location, color);
  }

  private final Point location;
  private final Color color;

  private ColorBlock(Stage stage, int size, Point location, Color color) {
    super(stage, size);
    this.location = Objects.requireNonNull(location).getLocation();
    this.color = Objects.requireNonNull(color);
  }

  @Override
  public void draw(Graphics g) {
    Offset offset = getStage().calculateOffset(getStage().player());
    g.setColor(color);
    g.fill3DRect(x() - offset.width(), y() - offset.height(), width(), height(), true);
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
