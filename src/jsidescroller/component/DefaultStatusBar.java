package jsidescroller.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Objects;
import jglib.util.StringDrawer;
import jsidescroller.common.StatusBar;

public class DefaultStatusBar extends StatusBar {

  private final Color backgroundColor;
  private final Color foregroundColor;
  private final Font font;

  public DefaultStatusBar() {
    this.backgroundColor = Color.CYAN;
    this.foregroundColor = Color.BLACK;
    this.font = new Font(Font.SANS_SERIF, Font.BOLD, height() - 10);
  }

  @Override
  public void draw(Graphics g) {
    Objects.requireNonNull(getStage(), "Stage is not set.");

    g.setColor(backgroundColor);
    g.fill3DRect(x(), y(), width(), height(), false);
    g.setColor(foregroundColor);
    g.draw3DRect(x(), y(), width() - 1, height() - 1, false);

    g.setFont(font);
    StringDrawer.LEFT.draw(g, x() + 10, y(), String.valueOf(getStage().elapseTimeCounter()));
    StringDrawer.LEFT.draw(g, x() + width() / 2, y(), String.valueOf(getStage().coinCounter()));
  }

  @Override
  public int height() {
    return 32;
  }
}
