package jsidescroller.common.counters;

import java.util.Objects;

abstract class CounterException extends Exception {

  private final AbstractCounter<?, ?> counter;
  private final Number operand;

  public CounterException(AbstractCounter<?, ?> counter, Number operand) {
    this.counter = Objects.requireNonNull(counter);
    this.operand = Objects.requireNonNull(operand);
  }

  public AbstractCounter<?, ?> getCounter() {
    return counter;
  }

  public Number getOperand() {
    return operand;
  }
}
