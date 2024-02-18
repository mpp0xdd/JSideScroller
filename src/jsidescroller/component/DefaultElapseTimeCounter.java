package jsidescroller.component;

import jsidescroller.common.counters.ElapseTimeCounter;

class DefaultElapseTimeCounter extends ElapseTimeCounter {

  public static DefaultElapseTimeCounter create() {
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
