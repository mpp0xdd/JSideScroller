package jsidescroller.common;

import jsidescroller.common.interfaces.Hittable;

public abstract class ItemBlock extends Chip implements Hittable {

  public ItemBlock(Stage stage, int size) {
    super(stage, size);
  }

  @Override
  public final Type type() {
    return Type.ITEM_BLOCK;
  }
}
