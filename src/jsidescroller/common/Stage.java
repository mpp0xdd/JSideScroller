package jsidescroller.common;

public abstract class Stage implements Drawable, Rectangular, Locatable, GravitationalField {

  private final Player player;

  public Stage() {
    this.player = newPlayer();
  }

  public Player player() {
    return player;
  }

  protected abstract Player newPlayer();
}
