package jsidescroller.common;

import java.util.Objects;
import jsidescroller.common.interfaces.Editable;
import jsidescroller.common.interfaces.Editor;
import jsidescroller.common.interfaces.Immutable;

public final class Velocity implements Immutable, Editable<Velocity> {

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

  @Override
  public VelocityEditor editor() {
    return this.new VelocityEditor();
  }

  public final class VelocityEditor implements Editor<Velocity> {

    private int x;
    private int y;

    private VelocityEditor() {
      this.x = Velocity.this.x();
      this.y = Velocity.this.y();
    }

    public VelocityEditor x(int x) {
      this.x = x;
      return this;
    }

    public VelocityEditor y(int y) {
      this.y = y;
      return this;
    }

    public VelocityEditor addY(int y) {
      this.y += y;
      return this;
    }

    @Override
    public Velocity edit() {
      return Velocity.of(x, y);
    }
  }
}
