package jsidescroller.common;

public class IntCounter extends Counter<Integer> {

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

  @Override
  protected boolean canIncrement() {
    return count + 1 <= maximumValue();
  }

  @Override
  protected boolean canDecrement() {
    return count - 1 >= minimumValue();
  }
}