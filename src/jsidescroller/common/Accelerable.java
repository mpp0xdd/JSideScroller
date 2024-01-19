package jsidescroller.common;

public interface Accelerable {

  int speed();

  Velocity velocity();

  void accelerate(Direction direction);

  void stop();
}
