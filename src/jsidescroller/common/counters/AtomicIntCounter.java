package jsidescroller.common.counters;

import java.util.concurrent.atomic.AtomicInteger;

abstract class AtomicIntCounter extends Counter<AtomicInteger, Integer> {

  @Override
  public void addExact(Integer value) throws AtomicIntCounterException {
    try {
      super.addExact(value);
    } catch (CounterException e) {
      throw (AtomicIntCounterException) e;
    }
  }

  @Override
  public void reset() {
    count.getAndSet(minimumValue());
  }

  @Override
  public Integer getCount() {
    return count.get();
  }

  @Override
  public Integer incrementAndGet() {
    return count.updateAndGet(this::increment);
  }

  @Override
  public Integer getAndIncrement() {
    return count.getAndUpdate(this::increment);
  }

  @Override
  public Integer decrementAndGet() {
    return count.updateAndGet(this::decrement);
  }

  @Override
  public Integer getAndDecrement() {
    return count.getAndUpdate(this::decrement);
  }

  @Override
  public Integer addAndGet(Integer value) {
    return count.accumulateAndGet(value, this::add);
  }

  @Override
  public Integer getAndAdd(Integer value) {
    return count.getAndAccumulate(value, this::add);
  }

  @Override
  public Integer addExactAndGet(Integer value) throws AtomicIntCounterException {
    try {
      return count.accumulateAndGet(value, this::addExact);
    } catch (ArithmeticException e) {
      throw newCounterException(value);
    }
  }

  @Override
  public Integer getAndAddExact(Integer value) throws AtomicIntCounterException {
    try {
      return count.getAndAccumulate(value, this::addExact);
    } catch (ArithmeticException e) {
      throw newCounterException(value);
    }
  }

  @Override
  public boolean isCounterStop() {
    return count.get() == maximumValue();
  }

  @Override
  protected AtomicInteger newInstance() {
    return new AtomicInteger();
  }

  @Override
  protected abstract AtomicIntCounterException newCounterException(Integer operand);

  private int increment(final int count) {
    final int incrementedCount = count + 1;
    return incrementedCount <= maximumValue() ? incrementedCount : count;
  }

  private int decrement(final int count) {
    final int decrementedCount = count - 1;
    return decrementedCount >= minimumValue() ? decrementedCount : count;
  }

  private int add(final int count, final int value) {
    int newCount = count + value;
    if (newCount < minimumValue()) {
      newCount = minimumValue();
    } else if (newCount > maximumValue()) {
      newCount = maximumValue();
    }
    return newCount;
  }

  private int addExact(final int count, final int value) throws ArithmeticException {
    int newCount = count + value;
    if (newCount < minimumValue() || newCount > maximumValue()) {
      throw new ArithmeticException();
    }
    return newCount;
  }
}
