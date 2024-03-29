package jsidescroller.common.counters;

public abstract class AbstractIntCounter extends AbstractCounter<Integer, Integer> {

  @Override
  public Integer getCount() {
    return count;
  }

  @Override
  public boolean isCounterStop() {
    return count.intValue() == minimumValue() || count.intValue() == maximumValue();
  }

  @Override
  public Integer incrementAndGet() {
    return canIncrement() ? ++count : count;
  }

  @Override
  public Integer getAndIncrement() {
    return canIncrement() ? count++ : count;
  }

  @Override
  public Integer decrementAndGet() {
    return canDecrement() ? --count : count;
  }

  @Override
  public Integer getAndDecrement() {
    return canDecrement() ? count-- : count;
  }

  @Override
  public Integer addAndGet(Integer value) {
    count = _add(value);
    return count;
  }

  @Override
  public Integer getAndAdd(Integer value) {
    try {
      return count;
    } finally {
      count = _add(value);
    }
  }

  @Override
  public Integer wrapAround(Integer value) {
    int newCount = count + value;
    WrapAroundCounter wrapAroundCounter = new WrapAroundCounter();
    while (true) {
      if (newCount < minimumValue()) {
        wrapAroundCounter.increment();
        newCount = maximumValue() - (minimumValue() - newCount);
        continue;
      }

      if (newCount > maximumValue()) {
        wrapAroundCounter.increment();
        newCount = minimumValue() + (newCount - maximumValue());
        continue;
      }

      break;
    }

    count = newCount;
    return wrapAroundCounter.getCount();
  }

  @Override
  public Integer incrementExactAndGet() throws CounterException {
    if (canIncrement()) {
      return ++count;
    }
    throw newCounterException(count);
  }

  @Override
  public Integer getAndIncrementExact() throws CounterException {
    if (canIncrement()) {
      return count++;
    }
    throw newCounterException(count);
  }

  @Override
  public Integer decrementExactAndGet() throws CounterException {
    if (canDecrement()) {
      return --count;
    }
    throw newCounterException(count);
  }

  @Override
  public Integer getAndDecrementExact() throws CounterException {
    if (canDecrement()) {
      return count--;
    }
    throw newCounterException(count);
  }

  @Override
  public Integer addExactAndGet(Integer value) throws CounterException {
    count = _addExact(value);
    return count;
  }

  @Override
  public Integer getAndAddExact(Integer value) throws CounterException {
    try {
      return count;
    } finally {
      count = _addExact(value);
    }
  }

  @Override
  public Integer capacity() {
    return maximumValue() - minimumValue();
  }

  @Override
  protected Integer newInstance() {
    return minimumValue();
  }

  private boolean canIncrement() {
    return count + 1 <= maximumValue();
  }

  private boolean canDecrement() {
    return count - 1 >= minimumValue();
  }

  private Integer _add(Integer value) {
    Integer newCount = count + value;
    if (newCount < minimumValue()) {
      newCount = minimumValue();
    } else if (newCount > maximumValue()) {
      newCount = maximumValue();
    }
    return newCount;
  }

  private Integer _addExact(Integer value) throws CounterException {
    Integer newCount = count + value;
    if (newCount < minimumValue() || newCount > maximumValue()) {
      throw newCounterException(value);
    }
    return newCount;
  }
}
