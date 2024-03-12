package jsidescroller.common.counters;

import java.util.Objects;
import java.util.Optional;

public class CounterException extends Exception {

  private final Counter<?> counter;
  private final Number operand;

  public CounterException(Counter<?> counter, Number operand) {
    this.counter = Objects.requireNonNull(counter);
    this.operand = operand;
  }

  public Counter<?> getCounter() {
    return counter;
  }

  public Optional<Number> getOperand() {
    return Optional.ofNullable(operand);
  }
}
