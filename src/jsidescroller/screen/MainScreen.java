package jsidescroller.screen;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;
import jglib.component.GameScreen;
import jsidescroller.common.Direction;
import jsidescroller.common.Keystroke;
import jsidescroller.common.Stage;
import jsidescroller.common.StatusBar;

public class MainScreen extends GameScreen implements KeyListener {

  private final StatusBar statusBar;
  private final Stage stage;

  private Keystroke aKey = Keystroke.NOT_PRESSED;
  private Keystroke dKey = Keystroke.NOT_PRESSED;
  private Keystroke spaceKey = Keystroke.NOT_PRESSED;

  public MainScreen(StatusBar statusBar, Stage stage) {
    this.statusBar = Objects.requireNonNull(statusBar);
    this.stage = Objects.requireNonNull(stage);

    this.statusBar.setStage(this.stage);
    this.stage.setLocation(0, this.statusBar.height());

    setScreenSize(stage.width(), statusBar.height() + stage.height());
    setFocusable(true);
    addKeyListener(this);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    paintSubGameScreen(g, statusBar);
    paintSubGameScreen(g, stage);
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
    stage.player().defeatEnemies();

    stage
        .enemies()
        .forEach(
            enemy -> {
              enemy.move();
              enemy.attack();
            });

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
