package jsidescroller.common;

public abstract class Coin extends Chip {

  private boolean isTaken;

  public Coin(Stage stage, int size) {
    super(stage, size);
    this.isTaken = false;
  }

  @Override
  public final Type type() {
    return Type.COIN;
  }

  public boolean isTaken() {
    return isTaken;
  }

  public boolean isNotTaken() {
    return !isTaken;
  }

  public void take() {
    if (isTaken()) return;

    System.err.printf("Coins in position (%d, %d) were taken.%n", x(), y());
    this.isTaken = true;
  }
}
