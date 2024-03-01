package jsidescroller.common.counters;

public abstract class ElapseTimeCounter extends IntCounter {

  @Override
  public void incrementExact() throws ElapseTimeCounterException {
    try {
      super.incrementExact();
    } catch (IntCounterException e) {
      throw (ElapseTimeCounterException) e;
    }
  }

  @Override
  public void decrementExact() throws ElapseTimeCounterException {
    try {
      super.decrementExact();
    } catch (IntCounterException e) {
      throw (ElapseTimeCounterException) e;
    }
  }

  @Override
  public Integer incrementExactAndGet() throws ElapseTimeCounterException {
    try {
      return super.incrementExactAndGet();
    } catch (IntCounterException e) {
      throw (ElapseTimeCounterException) e;
    }
  }

  @Override
  public Integer getAndIncrementExact() throws ElapseTimeCounterException {
    try {
      return super.getAndIncrementExact();
    } catch (IntCounterException e) {
      throw (ElapseTimeCounterException) e;
    }
  }

  @Override
  public Integer decrementExactAndGet() throws ElapseTimeCounterException {
    try {
      return super.decrementExactAndGet();
    } catch (IntCounterException e) {
      throw (ElapseTimeCounterException) e;
    }
  }

  @Override
  public Integer getAndDecrementExact() throws ElapseTimeCounterException {
    try {
      return super.getAndDecrementExact();
    } catch (IntCounterException e) {
      throw (ElapseTimeCounterException) e;
    }
  }

  @Override
  public void addExact(Integer value) throws ElapseTimeCounterException {
    try {
      super.addExact(value);
    } catch (IntCounterException e) {
      throw (ElapseTimeCounterException) e;
    }
  }

  @Override
  public Integer addExactAndGet(Integer value) throws ElapseTimeCounterException {
    try {
      return super.addExactAndGet(value);
    } catch (IntCounterException e) {
      throw (ElapseTimeCounterException) e;
    }
  }

  @Override
  public Integer getAndAddExact(Integer value) throws ElapseTimeCounterException {
    try {
      return super.getAndAddExact(value);
    } catch (IntCounterException e) {
      throw (ElapseTimeCounterException) e;
    }
  }

  @Override
  protected ElapseTimeCounterException newCounterException(Integer operand) {
    return new ElapseTimeCounterException(this, operand);
  }
}
