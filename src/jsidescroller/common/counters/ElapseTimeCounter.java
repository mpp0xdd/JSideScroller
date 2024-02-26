package jsidescroller.common.counters;

public abstract class ElapseTimeCounter extends IntCounter {

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
