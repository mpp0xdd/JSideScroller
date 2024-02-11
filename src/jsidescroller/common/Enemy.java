package jsidescroller.common;

import jsidescroller.common.interfaces.Mortal;
import jsidescroller.common.interfaces.Movable;
import jsidescroller.common.interfaces.Weighable;

public abstract class Enemy extends Chip implements Mortal, Movable, Weighable {

  public Enemy(Stage stage, int size) {
    super(stage, size);
  }

  @Override
  public final Type type() {
    return Type.ENEMY;
  }

  public abstract void attack();
}
