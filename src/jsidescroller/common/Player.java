package jsidescroller.common;

import jsidescroller.common.interfaces.Accelerable;
import jsidescroller.common.interfaces.Jumpable;
import jsidescroller.common.interfaces.Mortal;
import jsidescroller.common.interfaces.Movable;
import jsidescroller.common.interfaces.Weighable;

public abstract class Player extends Chip
    implements Mortal, Accelerable, Jumpable, Movable, Weighable {

  public Player(Stage stage) {
    super(stage);
  }

  @Override
  public final Type type() {
    return Type.PLAYER;
  }

  public abstract void collectCoins();

  public abstract void collectItems();

  public abstract void defeatEnemies();
}
