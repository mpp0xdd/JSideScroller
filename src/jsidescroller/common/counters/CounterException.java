package jsidescroller.common.counters;

import java.util.Objects;

abstract class CounterException extends Exception {

  private final Counter<?> counter;
  private final Number operand;

  public CounterException(AbstractCounter<?, ?> counter, Number operand) {
    this.counter = Objects.requireNonNull(counter);
    this.operand = Objects.requireNonNull(operand);
  }

  public Counter<?> getCounter() {
    return counter;
  }

  public Number getOperand() {
    return operand;
  }
}
