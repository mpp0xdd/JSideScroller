package jsidescroller.common.counters;

abstract class IntCounter extends Counter<Integer, Integer> {

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
  public Integer addExactAndGet(Integer value) {
    throw new RuntimeException("Not yet implemented."); // TODO
  }

  @Override
  public Integer getAndAddExact(Integer value) {
    throw new RuntimeException("Not yet implemented."); // TODO
  }

  @Override
  public boolean isCounterStop() {
    return count.intValue() == maximumValue();
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
}