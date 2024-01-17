package jsidescroller.common;

import java.util.Objects;

public abstract class Stage implements Drawable, Rectangular, Locatable, GravitationalField {

  private final Player player;

  public Stage(Player player) {
    this.player = Objects.requireNonNull(player);
  }

  public Player player() {
    return player;
  }
}
