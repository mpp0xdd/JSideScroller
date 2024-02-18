package jsidescroller.common;

class IntCounter extends Counter<Integer> {

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
  public boolean canIncrement() {
    return count + 1 <= maximumValue();
  }

  @Override
  public boolean canDecrement() {
    return count - 1 >= minimumValue();
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
}
