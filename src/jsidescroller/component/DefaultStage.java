package jsidescroller.component;

import java.awt.Graphics;
import jsidescroller.common.Player;
import jsidescroller.common.Stage;

public class DefaultStage extends Stage {

  @Override
  public void draw(Graphics g) {
    player().draw(g);
  }

  @Override
  public int width() {
    return 640;
  }

  @Override
  public int height() {
    return 480;
  }

  @Override
  public int gravity() {
    return 1;
  }

  @Override
  public void provideGravity() {
    player().accept(this);
  }

  @Override
  protected Player newPlayer() {
    return new DefaultPlayer(this);
  }
}
