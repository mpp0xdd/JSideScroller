package jsidescroller.common;

import jsidescroller.common.interfaces.Takable;

public abstract class Item extends Chip implements Takable {

  private boolean isTaken;

  public Item(Stage stage, int size) {
    super(stage, size);
    this.isTaken = false;
  }

  @Override
  public final Type type() {
    return Type.ITEM;
  }

  @Override
  public boolean isTaken() {
    return isTaken;
  }

  @Override
  public boolean isNotTaken() {
    return !isTaken;
  }

  @Override
  public void take() {
    if (isTaken()) return;
    this.isTaken = true;
  }
}
