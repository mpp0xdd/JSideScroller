package jsidescroller.common.counters;

abstract class IntCounter extends Counter<Integer, Integer> {

  @Override
  public void incrementExact() throws IntCounterException {
    try {
      super.incrementExact();
    } catch (CounterException e) {
      throw (IntCounterException) e;
    }
  }

  @Override
  public void decrementExact() throws IntCounterException {
    try {
      super.decrementExact();
    } catch (CounterException e) {
      throw (IntCounterException) e;
    }
  }

  @Override
  public void addExact(Integer value) throws IntCounterException {
    try {
      super.addExact(value);
    } catch (CounterException e) {
      throw (IntCounterException) e;
    }
  }

  @Override
  public Integer getCount() {
    return count;
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
  public Integer incrementExactAndGet() throws IntCounterException {
    if (canIncrement()) {
      return ++count;
    }
    throw newCounterException(count);
  }

  @Override
  public Integer getAndIncrementExact() throws IntCounterException {
    if (canIncrement()) {
      return count++;
    }
    throw newCounterException(count);
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
  public Integer decrementExactAndGet() throws IntCounterException {
    if (canDecrement()) {
      return --count;
    }
    throw newCounterException(count);
  }

  @Override
  public Integer getAndDecrementExact() throws IntCounterException {
    if (canDecrement()) {
      return count--;
    }
    throw newCounterException(count);
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
  public Integer addExactAndGet(Integer value) throws IntCounterException {
    count = _addExact(value);
    return count;
  }

  @Override
  public Integer getAndAddExact(Integer value) throws IntCounterException {
    try {
      return count;
    } finally {
      count = _addExact(value);
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
  public boolean isCounterStop() {
    return count.intValue() == maximumValue();
  }

  @Override
  protected Integer newInstance() {
    return minimumValue();
  }

  @Override
  protected Integer capacity() {
    return maximumValue() - minimumValue();
  }

  @Override
  protected abstract IntCounterException newCounterException(Integer operand);

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

  private Integer _addExact(Integer value) throws IntCounterException {
    Integer newCount = count + value;
    if (newCount < minimumValue() || newCount > maximumValue()) {
      throw newCounterException(value);
    }
    return newCount;
  }
}
