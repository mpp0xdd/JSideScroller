package jsidescroller.common;

import java.util.Objects;
import jsidescroller.common.interfaces.Immutable;

public final class StagePoint implements Immutable {

  public static StagePoint of(Stage stage, int x, int y) {
    return new StagePoint(stage, x, y);
  }

  private final Stage stage;
  private final int x;
  private final int y;

  private StagePoint(Stage stage, int x, int y) {
    Objects.requireNonNull(stage);
    if (x < 0 || x >= stage.columns()) {
      throw new IllegalArgumentException("x:" + x);
    }
    if (y < 0 || y >= stage.rows()) {
      throw new IllegalArgumentException("y:" + y);
    }

    this.stage = stage;
    this.x = x;
    this.y = y;
  }

  public Stage getStage() {
    return stage;
  }

  public int x() {
    return x;
  }

  public int y() {
    return y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(stage, x, y);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    StagePoint other = (StagePoint) obj;
    return Objects.equals(stage, other.stage) && x == other.x && y == other.y;
  }
}
