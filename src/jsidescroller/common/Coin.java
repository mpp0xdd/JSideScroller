package jsidescroller.common;

import jsidescroller.common.interfaces.Takable;

public abstract class Coin extends Sprite implements Takable {

  private boolean isTaken;

  public Coin(Stage stage, int width, int height) {
    super(stage, width, height);
    this.isTaken = false;
  }

  @Override
  public boolean isTaken() {
    return isTaken;
  }

  @Override
  public boolean isNotTaken() {
    return !isTaken;
  }

  @Override
  public void take() {
    if (isTaken()) return;
    this.isTaken = true;
  }
}
