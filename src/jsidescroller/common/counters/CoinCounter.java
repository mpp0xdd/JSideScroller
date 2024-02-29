package jsidescroller.common.counters;

public abstract class CoinCounter extends IntCounter {

  @Override
  public void addExact(Integer value) throws CoinCounterException {
    try {
      super.addExact(value);
    } catch (IntCounterException e) {
      throw (CoinCounterException) e;
    }
  }

  @Override
  public Integer addExactAndGet(Integer value) throws CoinCounterException {
    try {
      return super.addExactAndGet(value);
    } catch (IntCounterException e) {
      throw (CoinCounterException) e;
    }
  }

  @Override
  public Integer getAndAddExact(Integer value) throws CoinCounterException {
    try {
      return super.getAndAddExact(value);
    } catch (IntCounterException e) {
      throw (CoinCounterException) e;
    }
  }

  @Override
  protected CoinCounterException newCounterException(Integer operand) {
    return new CoinCounterException(this, operand);
  }
}
