package jsidescroller.common;

import jsidescroller.common.interfaces.Accelerable;
import jsidescroller.common.interfaces.Jumpable;
import jsidescroller.common.interfaces.Movable;
import jsidescroller.common.interfaces.Weighable;

public abstract class Player extends Chip implements Accelerable, Jumpable, Movable, Weighable {

  public Player(Stage stage, int size) {
    super(stage, size);
  }

  @Override
  public final Type type() {
    return Type.PLAYER;
  }

  public final void collectCoins() {
    getStage().coins().stream().filter(this::intersects).forEach(Coin::take);
  }
}
