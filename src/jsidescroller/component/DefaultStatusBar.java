package jsidescroller.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import jglib.util.GameUtilities;
import jsidescroller.common.ElapseTimeCounter;
import jsidescroller.common.StatusBar;

public class DefaultStatusBar extends StatusBar {

  private final Color backgroundColor;
  private final Color foregroundColor;
  private final Font font;

  private final ElapseTimeCounter elapseTimeCounter;

  public DefaultStatusBar() {
    this.backgroundColor = Color.CYAN;
    this.foregroundColor = Color.BLACK;
    this.font = new Font(Font.SANS_SERIF, Font.BOLD, height() - 10);

    this.elapseTimeCounter = new DefaultElapseTimeCounter();

    Timer timer = new Timer();
    timer.schedule(
        new TimerTask() {
          @Override
          public void run() {
            elapseTimeCounter.increment();
          }
        },
        1000L,
        1000L);
  }

  @Override
  public void draw(Graphics g) {
    Objects.requireNonNull(getStage(), "Stage is not set.");

    g.setColor(backgroundColor);
    g.fill3DRect(x(), y(), width(), height(), false);
    g.setColor(foregroundColor);
    g.draw3DRect(x(), y(), width() - 1, height() - 1, false);

    g.setFont(font);
    GameUtilities.drawString(g, x() + 10, y(), String.valueOf(elapseTimeCounter));
  }

  @Override
  public int height() {
    return 32;
  }
}
