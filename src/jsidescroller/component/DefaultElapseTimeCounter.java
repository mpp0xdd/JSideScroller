package jsidescroller.component;

import jsidescroller.common.counters.ElapseTimeCounter;
import jsidescroller.common.counters.AbstractIntCounter;

class DefaultElapseTimeCounter extends AbstractIntCounter implements ElapseTimeCounter {

  public static DefaultElapseTimeCounter create() {
    return new DefaultElapseTimeCounter();
  }

  private DefaultElapseTimeCounter() {}

  @Override
  public Integer minimumValue() {
    return 0;
  }

  @Override
  public Integer maximumValue() {
    return 300;
  }
}
