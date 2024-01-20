package jsidescroller.common;

import java.util.Objects;

public final class Velocity {

  public static final Velocity ZERO = of(0, 0);

  public static Velocity of(int vx, int vy) {
    return new Velocity(vx, vy);
  }

  private final int x;
  private final int y;

  private Velocity(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int x() {
    return x;
  }

  public int y() {
    return y;
  }

  public boolean isLeftward() {
    return x < 0;
  }

  public boolean isRightward() {
    return x > 0;
  }

  public boolean isUpward() {
    return y < 0;
  }

  public boolean isDownward() {
    return y > 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Velocity other = (Velocity) obj;
    return x == other.x && y == other.y;
  }
}
