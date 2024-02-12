package jsidescroller.common;

public abstract class Block extends Chip {

  public Block(Stage stage) {
    super(stage);
  }

  @Override
  public final Type type() {
    return Type.BLOCK;
  }
}
