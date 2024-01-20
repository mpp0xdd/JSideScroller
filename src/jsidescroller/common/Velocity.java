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

  public boolean isZero() {
    return this.equals(ZERO);
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

  public Editor editor() {
    return this.new Editor();
  }

  public final class Editor {

    private int x;
    private int y;

    private Editor() {
      this.x = Velocity.this.x();
      this.y = Velocity.this.y();
    }

    public Editor x(int x) {
      this.x = x;
      return this;
    }

    public Editor y(int y) {
      this.y = y;
      return this;
    }

    public Editor addY(int y) {
      this.y += y;
      return this;
    }

    public Velocity edit() {
      return Velocity.of(x, y);
    }
  }
}
