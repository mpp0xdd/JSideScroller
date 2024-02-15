package jsidescroller.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Objects;
import jsidescroller.common.Block;
import jsidescroller.common.Stage;
import jsidescroller.common.StageOffset;

class ColorBlock extends Block {

  public static ColorBlock of(Stage stage, Point location, Color color) {
    return new ColorBlock(stage, location, color);
  }

  private final Point location;
  private final Color color;

  private ColorBlock(Stage stage, Point location, Color color) {
    super(stage);
    this.location = Objects.requireNonNull(location).getLocation();
    this.color = Objects.requireNonNull(color);
  }

  @Override
  public void draw(Graphics g) {
    StageOffset offset = StageOffset.of(getStage());
    Point point = offset.apply(this);

    g.setColor(color);
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
}
