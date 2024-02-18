package jsidescroller.common;

abstract class IntCounter extends Counter<Integer> {

  @Override
  public Integer preIncrement() {
    return canIncrement() ? ++count : count;
  }

  @Override
  public Integer postIncrement() {
    return canIncrement() ? count++ : count;
  }

  @Override
  public Integer preDecrement() {
    return canDecrement() ? --count : count;
  }

  @Override
  public Integer postDecrement() {
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
  protected Integer defaultValue() {
    return minimumValue();
  }

  @Override
  protected Integer minimumValue() {
    return 0;
  }

  @Override
  protected Integer maximumValue() {
    return Integer.MAX_VALUE;
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
