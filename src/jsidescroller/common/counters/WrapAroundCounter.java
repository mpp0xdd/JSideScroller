package jsidescroller.common.counters;

class WrapAroundCounter extends IntCounter {

  @Override
  protected Integer minimumValue() {
    return 0;
  }

  @Override
  protected Integer maximumValue() {
    return Integer.MAX_VALUE;
  }
}
