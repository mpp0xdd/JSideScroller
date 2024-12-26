package jsidescroller.screen;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Objects;
import jglib.component.GameScreen;
import jglib.util.model.Key;
import jglib.util.model.Keyboard;
import jsidescroller.common.Direction;
import jsidescroller.common.Stage;
import jsidescroller.common.StatusBar;

public class MainScreen extends GameScreen {

  private enum OperationKey implements Key {
    A(KeyEvent.VK_A),
    D(KeyEvent.VK_D),
    SPACE(KeyEvent.VK_SPACE);

    private final int code;

    private OperationKey(int code) {
      this.code = code;
    }

    @Override
    public int code() {
      return code;
    }
  }

  private final StatusBar statusBar;
  private final Stage stage;
  private final Keyboard<OperationKey> keyboard;

  public MainScreen(StatusBar statusBar, Stage stage) {
    this.statusBar = Objects.requireNonNull(statusBar);
    this.stage = Objects.requireNonNull(stage);

    this.statusBar.setStage(this.stage);
    this.stage.setLocation(0, this.statusBar.height());

    this.keyboard = Keyboard.create(OperationKey.values());

    setScreenSize(stage.width(), statusBar.height() + stage.height());
    setFocusable(true);
    addKeyListener(keyboard.asKeyListener());
  }

  @Override
  protected void paintGameComponent(Graphics g) {
    paintSubGameScreen(g, statusBar);
    paintSubGameScreen(g, stage);
  }

  @Override
  protected void runGameLoop() {
    if (keyboard.isPressed(OperationKey.A)) {
      stage.player().accelerate(Direction.LEFT);
    } else if (keyboard.isPressed(OperationKey.D)) {
      stage.player().accelerate(Direction.RIGHT);
    } else {
      stage.player().stop();
    }

    if (keyboard.isPressed(OperationKey.SPACE)) {
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
  }
}
