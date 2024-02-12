package jsidescroller.common;

import jsidescroller.common.interfaces.Mortal;
import jsidescroller.common.interfaces.Movable;
import jsidescroller.common.interfaces.Weighable;

public abstract class Enemy extends Sprite implements Mortal, Movable, Weighable {

  public Enemy(Stage stage, int width, int height) {
    super(stage, width, height);
  }

  public abstract void attack();
}
