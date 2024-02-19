package jsidescroller.component;

import jsidescroller.common.counters.CoinCounter;

public class DefaultCoinCounter extends CoinCounter {

  public static DefaultCoinCounter create() {
    return new DefaultCoinCounter();
  }

  private DefaultCoinCounter() {}

  @Override
  protected Integer minimumValue() {
    return 0;
  }

  @Override
  protected Integer maximumValue() {
    return 60;
  }
}
