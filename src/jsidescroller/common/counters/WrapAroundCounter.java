package jsidescroller.common.counters;

class WrapAroundCounter extends IntCounter {

  @Override
  public void incrementExact() throws WrapAroundCounterException {
    try {
      super.incrementExact();
    } catch (IntCounterException e) {
      throw (WrapAroundCounterException) e;
    }
  }

  @Override
  public void decrementExact() throws WrapAroundCounterException {
    try {
      super.decrementExact();
    } catch (IntCounterException e) {
      throw (WrapAroundCounterException) e;
    }
  }

  @Override
  public Integer incrementExactAndGet() throws WrapAroundCounterException {
    try {
      return super.incrementExactAndGet();
    } catch (IntCounterException e) {
      throw (WrapAroundCounterException) e;
    }
  }

  @Override
  public Integer getAndIncrementExact() throws WrapAroundCounterException {
    try {
      return super.getAndIncrementExact();
    } catch (IntCounterException e) {
      throw (WrapAroundCounterException) e;
    }
  }

  @Override
  public Integer decrementExactAndGet() throws WrapAroundCounterException {
    try {
      return super.decrementExactAndGet();
    } catch (IntCounterException e) {
      throw (WrapAroundCounterException) e;
    }
  }

  @Override
  public Integer getAndDecrementExact() throws WrapAroundCounterException {
    try {
      return super.getAndDecrementExact();
    } catch (IntCounterException e) {
      throw (WrapAroundCounterException) e;
    }
  }

  @Override
  public void addExact(Integer value) throws WrapAroundCounterException {
    try {
      super.addExact(value);
    } catch (IntCounterException e) {
      throw (WrapAroundCounterException) e;
    }
  }

  @Override
  public Integer addExactAndGet(Integer value) throws WrapAroundCounterException {
    try {
      return super.addExactAndGet(value);
    } catch (IntCounterException e) {
      throw (WrapAroundCounterException) e;
    }
  }

  @Override
  public Integer getAndAddExact(Integer value) throws WrapAroundCounterException {
    try {
      return super.getAndAddExact(value);
    } catch (IntCounterException e) {
      throw (WrapAroundCounterException) e;
    }
  }

  @Override
  protected WrapAroundCounterException newCounterException(Integer operand) {
    return new WrapAroundCounterException(this, operand);
  }

  @Override
  protected Integer minimumValue() {
    return 0;
  }

  @Override
  protected Integer maximumValue() {
    return Integer.MAX_VALUE;
  }
}
