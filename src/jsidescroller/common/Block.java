package jsidescroller.common;

public abstract class Block extends Chip {

  public Block(Stage stage, int size) {
    super(stage, size);
  }

  @Override
  public final Type type() {
    return Type.BLOCK;
  }
}
