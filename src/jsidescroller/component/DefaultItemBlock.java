package jsidescroller.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Objects;
import jglib.util.GameUtilities;
import jsidescroller.common.Item;
import jsidescroller.common.ItemBlock;
import jsidescroller.common.Stage;
import jsidescroller.common.StageOffset;

class DefaultItemBlock extends ItemBlock {

  public static DefaultItemBlock of(Stage stage, Point location) {
    return new DefaultItemBlock(stage, location);
  }

  private final Point location;
  private boolean isHit;

  private final Color BLOCK_COLOR;
  private final Color QUESTION_COLOR;
  private final Font QUESTION_FONT;

  private DefaultItemBlock(Stage stage, Point location) {
    super(stage);
    this.location = Objects.requireNonNull(location).getLocation();
    this.isHit = false;

    this.BLOCK_COLOR = new Color(252, 152, 56);
    this.QUESTION_COLOR = new Color(200, 76, 11);
    this.QUESTION_FONT = new Font(Font.SANS_SERIF, Font.BOLD, height());
  }

  @Override
  public void draw(Graphics g) {
    StageOffset offset = StageOffset.of(getStage(), getStage().player());
    Point point = offset.apply(this);

    g.setColor(isHit ? QUESTION_COLOR : BLOCK_COLOR);
    g.fill3DRect(point.x, point.y, width(), height(), true);

    if (!isHit) {
      g.setColor(QUESTION_COLOR);
      g.setFont(QUESTION_FONT);
      GameUtilities.drawStringAfterCentering(g, point.x + width() / 2, point.y + height() / 2, "?");
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
    Item item = DefaultItem.of(getStage(), width(), height(), itemLocation);
    getStage().add(item);
    this.isHit = true;
  }
}
