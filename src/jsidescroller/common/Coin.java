package jsidescroller.common;

public abstract class Coin extends Chip {

  public Coin(Stage stage, int size) {
    super(stage, size);
  }

  @Override
  public final Type type() {
    return Type.COIN;
  }
}
