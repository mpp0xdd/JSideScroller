package jsidescroller.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import jsidescroller.common.Player;

public class DefaultPlayer implements Player {

  @Override
  public void draw(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    g2.setColor(Color.RED);
    g2.fill(asRectangle());
  }

  @Override
  public int x() {
    return 255;
  }

  @Override
  public int y() {
    return 255;
  }
}
