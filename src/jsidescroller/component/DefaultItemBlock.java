package jsidescroller.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Objects;
import jglib.util.GameUtilities;
import jsidescroller.common.Item;
import jsidescroller.common.ItemBlock;
import jsidescroller.common.Stage;

class DefaultItemBlock extends ItemBlock {

  private final Point location;
  private boolean isHit;

  private final Color BLOCK_COLOR;
  private final Color QUESTION_COLOR;
  private final Font QUESTION_FONT;

  public DefaultItemBlock(Stage stage, int size, Point location) {
    super(stage, size);
    this.location = Objects.requireNonNull(location).getLocation();
    this.isHit = false;

    this.BLOCK_COLOR = new Color(252, 152, 56);
    this.QUESTION_COLOR = new Color(200, 76, 11);
    this.QUESTION_FONT = new Font(Font.SANS_SERIF, Font.BOLD, height());
  }

  @Override
  public void draw(Graphics g) {
    Dimension offset = getStage().calculateOffset(getStage().player());

    final Color blockColor = isHit ? QUESTION_COLOR : BLOCK_COLOR;

    g.setColor(blockColor);
    g.fill3DRect(x() - offset.width, y() - offset.height, width(), height(), true);

    if (!isHit) {
      g.setColor(QUESTION_COLOR);
      g.setFont(QUESTION_FONT);
      GameUtilities.drawStringAfterCentering(
          g, x() - offset.width + width() / 2, y() - offset.height + height() / 2, "?");
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

  @Override
  public void hit() {
    if (isHit) return;

    Point itemLocation = this.getLocation();
    itemLocation.translate(0, -this.height());
    Item item = new DefaultItem(getStage(), getStage().chipSize(), itemLocation);
    getStage().add(item);
    this.isHit = true;
  }
}
