package jsidescroller.common.counters;

public class CoinCounterException extends IntCounterException {

  public CoinCounterException(CoinCounter counter, Integer operand) {
    super(counter, operand);
  }

  @Override
  public CoinCounter getCounter() {
    return (CoinCounter) super.getCounter();
  }
}
