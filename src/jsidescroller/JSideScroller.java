package jsidescroller;

import jglib.core.Game;
import jglib.screen.GameWindow;
import jsidescroller.component.DefaultStage;
import jsidescroller.component.DefaultStatusBar;
import jsidescroller.screen.MainScreen;

public class JSideScroller extends Game {

  public static void main(String[] args) {
    launch(JSideScroller.class);
  }

  @Override
  protected void start() {
    GameWindow window = new GameWindow("JSideScroller");
    MainScreen screen = new MainScreen(new DefaultStatusBar(), new DefaultStage());

    window.switchGameScreen(screen);
    window.setVisible(true);
    screen.startGameLoop();
  }
}
