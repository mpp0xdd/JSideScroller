package jsidescroller.common;

public abstract class Player extends Chip implements Accelerable, Jumpable, Movable, Weighable {
  public Player(int size) {
    super(size);
  }
}
