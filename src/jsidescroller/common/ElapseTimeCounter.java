package jsidescroller.common;

public final class ElapseTimeCounter extends IntCounter {

  @Override
  protected Integer minimumValue() {
    return 0;
  }

  @Override
  protected Integer maximumValue() {
    return 300;
  }
}
