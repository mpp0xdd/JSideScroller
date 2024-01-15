package jsidescroller.screen;

import java.awt.Color;
import java.awt.Graphics;
import jglib.component.GameScreen;
import jsidescroller.common.Player;
import jsidescroller.component.DefaultPlayer;

public class MainScreen extends GameScreen {

  private final Player player = new DefaultPlayer();

  public MainScreen() {
    setBackground(Color.BLACK);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    player.draw(g);
  }

  @Override
  protected void runGameLoop() {
    // nop
  }
}
