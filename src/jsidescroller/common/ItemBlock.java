package jsidescroller.common;

public abstract class ItemBlock extends Chip {

  public ItemBlock(Stage stage, int size) {
    super(stage, size);
  }

  @Override
  public final Type type() {
    return Type.ITEM_BLOCK;
  }

  public abstract void hit();
}
