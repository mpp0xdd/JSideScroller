package jsidescroller.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Objects;
import jglib.util.GameUtilities;
import jsidescroller.common.ItemBlock;
import jsidescroller.common.Stage;

class DefaultItemBlock extends ItemBlock {

  private final Point location;

  public DefaultItemBlock(Stage stage, int size, Point location) {
    super(stage, size);
    this.location = Objects.requireNonNull(location).getLocation();
  }

  @Override
  public void draw(Graphics g) {
    Dimension offset = getStage().calculateOffset(getStage().player());
    g.setColor(new Color(252, 152, 56));
    g.fill3DRect(x() - offset.width, y() - offset.height, width(), height(), true);
    g.setColor(new Color(200, 76, 11));
    g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, height()));
    GameUtilities.drawStringAfterCentering(
        g, x() - offset.width + width() / 2, y() - offset.height + height() / 2, "?");
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
  public void hit() {
    // nop
  }
}
