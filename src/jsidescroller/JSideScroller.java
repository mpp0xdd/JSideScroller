package jsidescroller;

import jglib.component.GameWindow;
import jsidescroller.screen.MainScreen;

public class JSideScroller {

  public static void main(String[] args) {
    GameWindow window = new GameWindow("JSideScroller");
    MainScreen screen = new MainScreen();

    window.switchGameScreen(screen);
    window.setVisible(true);
    screen.startGameLoop();
  }
}
