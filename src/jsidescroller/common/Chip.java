package jsidescroller.common;

import jsidescroller.common.interfaces.SideScrollerComponent;

public abstract class Chip implements SideScrollerComponent {

  public enum Type {
    PLAYER,
    BLOCK,
    VOID,
  }

  private final int size;

  public Chip(int size) {
    this.size = size;
  }

  @Override
  public final int width() {
    return size;
  }

  @Override
  public final int height() {
    return size;
  }

  public boolean isBlock() {
    return this.type().equals(Type.BLOCK);
  }

  public abstract Type type();
}
