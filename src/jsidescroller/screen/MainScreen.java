package jsidescroller.screen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import jglib.component.GameScreen;
import jsidescroller.common.Direction;
import jsidescroller.common.Keystroke;
import jsidescroller.common.Player;
import jsidescroller.component.DefaultPlayer;

public class MainScreen extends GameScreen implements KeyListener {

  private final Player player = new DefaultPlayer();
  private Keystroke aKey = Keystroke.NOT_PRESSED;
  private Keystroke dKey = Keystroke.NOT_PRESSED;

  public MainScreen() {
    setBackground(Color.BLACK);
    setFocusable(true);
    addKeyListener(this);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    player.draw(g);
  }

  @Override
  protected void runGameLoop() {
    if (aKey.isPressed()) {
      player.accelerate(Direction.LEFT);
    } else if (dKey.isPressed()) {
      player.accelerate(Direction.RIGHT);
    } else {
      player.stop();
    }

    player.move();

    repaint();
  }

  @Override
  public void keyTyped(KeyEvent e) {
    // nop
  }

  @Override
  public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
      case KeyEvent.VK_A -> aKey = Keystroke.PRESSED;
      case KeyEvent.VK_D -> dKey = Keystroke.PRESSED;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    switch (e.getKeyCode()) {
      case KeyEvent.VK_A -> aKey = Keystroke.NOT_PRESSED;
      case KeyEvent.VK_D -> dKey = Keystroke.NOT_PRESSED;
    }
  }
}
