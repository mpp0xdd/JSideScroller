package jsidescroller.component;

import jsidescroller.common.ElapseTimeCounter;

class DefaultElapseTimeCounter extends ElapseTimeCounter {

  public static DefaultElapseTimeCounter createDefaultElapseTimeCounter() {
    return new DefaultElapseTimeCounter();
  }

  private DefaultElapseTimeCounter() {}

  @Override
  protected Integer minimumValue() {
    return 0;
  }

  @Override
  protected Integer maximumValue() {
    return 300;
  }
}
