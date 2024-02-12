package jsidescroller.common;

import jsidescroller.common.interfaces.Hittable;

public abstract class ItemBlock extends Chip implements Hittable {

  public ItemBlock(Stage stage) {
    super(stage);
  }

  @Override
  public final Type type() {
    return Type.ITEM_BLOCK;
  }
}
