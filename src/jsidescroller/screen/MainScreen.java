package jsidescroller.screen;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import jglib.component.GameScreen;
import jsidescroller.common.Direction;
import jsidescroller.common.Keystroke;
import jsidescroller.common.Stage;
import jsidescroller.component.DefaultStage;

public class MainScreen extends GameScreen implements KeyListener {

  private final Stage stage = new DefaultStage();
  private Keystroke aKey = Keystroke.NOT_PRESSED;
  private Keystroke dKey = Keystroke.NOT_PRESSED;
  private Keystroke spaceKey = Keystroke.NOT_PRESSED;

  public MainScreen() {
    setScreenSize(stage.width(), stage.height());
    setFocusable(true);
    addKeyListener(this);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    stage.draw(g);
  }

  @Override
  protected void runGameLoop() {
    if (aKey.isPressed()) {
      stage.player().accelerate(Direction.LEFT);
    } else if (dKey.isPressed()) {
      stage.player().accelerate(Direction.RIGHT);
    } else {
      stage.player().stop();
    }

    if (spaceKey.isPressed()) {
      stage.player().jump();
    }

    stage.player().move();
    stage.player().collectCoins();
    stage.player().collectItems();

    stage.provideGravity();

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
      case KeyEvent.VK_SPACE -> spaceKey = Keystroke.PRESSED;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    switch (e.getKeyCode()) {
      case KeyEvent.VK_A -> aKey = Keystroke.NOT_PRESSED;
      case KeyEvent.VK_D -> dKey = Keystroke.NOT_PRESSED;
      case KeyEvent.VK_SPACE -> spaceKey = Keystroke.NOT_PRESSED;
    }
  }
}
