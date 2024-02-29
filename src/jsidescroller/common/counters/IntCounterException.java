package jsidescroller.common.counters;

abstract class IntCounterException extends CounterException {

  public IntCounterException(IntCounter counter, Integer operand) {
    super(counter, operand);
  }

  @Override
  public IntCounter getCounter() {
    return (IntCounter) super.getCounter();
  }

  @Override
  public Integer getOperand() {
    return (Integer) super.getOperand();
  }
}
