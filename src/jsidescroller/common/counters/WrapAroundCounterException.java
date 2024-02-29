package jsidescroller.common.counters;

class WrapAroundCounterException extends IntCounterException {

  public WrapAroundCounterException(WrapAroundCounter counter, Integer operand) {
    super(counter, operand);
  }

  @Override
  public WrapAroundCounter getCounter() {
    return (WrapAroundCounter) super.getCounter();
  }
}
