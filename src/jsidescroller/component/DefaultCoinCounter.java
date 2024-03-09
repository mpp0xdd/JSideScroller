package jsidescroller.component;

import jsidescroller.common.counters.CoinCounter;
import jsidescroller.common.counters.AbstractIntCounter;

class DefaultCoinCounter extends AbstractIntCounter implements CoinCounter {

  public static DefaultCoinCounter create() {
    return new DefaultCoinCounter();
  }

  private DefaultCoinCounter() {}

  @Override
  public Integer minimumValue() {
    return 0;
  }

  @Override
  public Integer maximumValue() {
    return 60;
  }
}
