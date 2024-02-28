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
  public Integer incrementExactAndGet() throws AtomicIntCounterException {
    try {
      return count.updateAndGet(this::incrementExact);
    } catch (ArithmeticException e) {
      throw newCounterException(getCount());
    }
  }

  @Override
  public Integer getAndIncrementExact() throws AtomicIntCounterException {
    try {
      return count.getAndUpdate(this::incrementExact);
    } catch (ArithmeticException e) {
      throw newCounterException(getCount());
    }
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
  public Integer decrementExactAndGet() throws AtomicIntCounterException {
    try {
      return count.updateAndGet(this::decrementExact);
    } catch (ArithmeticException e) {
      throw newCounterException(getCount());
    }
  }

  @Override
  public Integer getAndDecrementExact() throws AtomicIntCounterException {
    try {
      return count.getAndUpdate(this::decrementExact);
    } catch (ArithmeticException e) {
      throw newCounterException(getCount());
    }
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
  public Integer wrapAround(Integer value) {
    final WrapAroundCounter wrapAroundCounter = new WrapAroundCounter();

    count.getAndAccumulate(
        value,
        (_count, _value) -> {
          wrapAroundCounter.reset();
          int newCount = _count + _value;
          while (true) {
            if (newCount < minimumValue()) {
              newCount = maximumValue() - (minimumValue() - newCount);
              wrapAroundCounter.increment();
              continue;
            }

            if (newCount > maximumValue()) {
              newCount = minimumValue() + (newCount - maximumValue());
              wrapAroundCounter.increment();
              continue;
            }

            break;
          }
          return newCount;
        });

    return wrapAroundCounter.getCount();
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
  protected Integer capacity() {
    return maximumValue() - minimumValue();
  }

  @Override
  protected abstract AtomicIntCounterException newCounterException(Integer operand);

  private int increment(final int count) {
    final int incrementedCount = count + 1;
    return incrementedCount <= maximumValue() ? incrementedCount : count;
  }

  private int incrementExact(final int count) throws ArithmeticException {
    final int incrementedCount = count + 1;
    if (incrementedCount <= maximumValue()) {
      return incrementedCount;
    }
    throw new ArithmeticException();
  }

  private int decrement(final int count) {
    final int decrementedCount = count - 1;
    return decrementedCount >= minimumValue() ? decrementedCount : count;
  }

  private int decrementExact(final int count) {
    final int decrementedCount = count - 1;
    if (decrementedCount >= minimumValue()) {
      return decrementedCount;
    }
    throw new ArithmeticException();
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
