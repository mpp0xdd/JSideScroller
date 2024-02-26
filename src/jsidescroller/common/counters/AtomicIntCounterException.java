package jsidescroller.common.counters;

abstract class AtomicIntCounterException extends CounterException {

  public AtomicIntCounterException(AtomicIntCounter counter, Integer operand) {
    super(counter, operand);
  }

  @Override
  public AtomicIntCounter getCounter() {
    return (AtomicIntCounter) super.getCounter();
  }

  @Override
  public Integer getOperand() {
    return (Integer) super.getOperand();
  }
}
