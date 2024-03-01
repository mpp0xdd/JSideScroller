package jsidescroller.common.counters;

public abstract class CoinCounter extends IntCounter {

  @Override
  public void incrementExact() throws CoinCounterException {
    try {
      super.incrementExact();
    } catch (IntCounterException e) {
      throw (CoinCounterException) e;
    }
  }

  @Override
  public void decrementExact() throws CoinCounterException {
    try {
      super.decrementExact();
    } catch (IntCounterException e) {
      throw (CoinCounterException) e;
    }
  }

  @Override
  public Integer incrementExactAndGet() throws CoinCounterException {
    try {
      return super.incrementExactAndGet();
    } catch (IntCounterException e) {
      throw (CoinCounterException) e;
    }
  }

  @Override
  public Integer getAndIncrementExact() throws CoinCounterException {
    try {
      return super.getAndIncrementExact();
    } catch (IntCounterException e) {
      throw (CoinCounterException) e;
    }
  }

  @Override
  public Integer decrementExactAndGet() throws CoinCounterException {
    try {
      return super.decrementExactAndGet();
    } catch (IntCounterException e) {
      throw (CoinCounterException) e;
    }
  }

  @Override
  public Integer getAndDecrementExact() throws CoinCounterException {
    try {
      return super.getAndDecrementExact();
    } catch (IntCounterException e) {
      throw (CoinCounterException) e;
    }
  }

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
