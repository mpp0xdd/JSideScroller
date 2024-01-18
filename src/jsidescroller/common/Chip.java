package jsidescroller.common;

public abstract class Chip implements SideScrollerComponent {

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

  public abstract ChipType chipType();
}
