package jsidescroller.component;

import java.util.Timer;
import java.util.TimerTask;
import jsidescroller.common.counters.AbstractIntCounter;
import jsidescroller.common.counters.ElapseTimeCounter;

class DefaultElapseTimeCounter extends AbstractIntCounter implements ElapseTimeCounter {

  public static DefaultElapseTimeCounter create() {
    return new DefaultElapseTimeCounter();
  }

  private final Timer timer;

  private DefaultElapseTimeCounter() {
    this.timer = new Timer();
  }

  @Override
  public Integer minimumValue() {
    return 0;
  }

  @Override
  public Integer maximumValue() {
    return 300;
  }

  @Override
  public void start() {
    timer.schedule(
        new TimerTask() {
          @Override
          public void run() {
            increment();
          }
        },
        1000L,
        1000L);
  }
}
