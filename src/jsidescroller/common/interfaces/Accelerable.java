package jsidescroller.common.interfaces;

import jsidescroller.common.Direction;
import jsidescroller.common.Velocity;

public interface Accelerable {

  int speed();

  Velocity velocity();

  void accelerate(Direction direction);

  void stop();
}
