package jsidescroller.common.counters;

class WrapAroundCounter extends AbstractIntCounter {

  @Override
  public Integer minimumValue() {
    return 0;
  }

  @Override
  public Integer maximumValue() {
    return Integer.MAX_VALUE;
  }
}
