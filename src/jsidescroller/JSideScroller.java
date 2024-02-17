package jsidescroller;

import jglib.component.GameWindow;
import jsidescroller.component.DefaultStage;
import jsidescroller.component.DefaultStatusBar;
import jsidescroller.screen.MainScreen;

public class JSideScroller {

  public static void main(String[] args) {
    GameWindow window = new GameWindow("JSideScroller");
    MainScreen screen = new MainScreen(new DefaultStatusBar(), new DefaultStage());

    window.switchGameScreen(screen);
    window.setVisible(true);
    screen.startGameLoop();
  }
}
