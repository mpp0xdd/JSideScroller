package jsidescroller.common.counters;

public class ElapseTimeCounterException extends IntCounterException {

  public ElapseTimeCounterException(ElapseTimeCounter counter, Integer operand) {
    super(counter, operand);
  }

  @Override
  public ElapseTimeCounter getCounter() {
    return (ElapseTimeCounter) super.getCounter();
  }
}
