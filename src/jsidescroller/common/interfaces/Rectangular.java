package jsidescroller.common.interfaces;

import java.awt.Rectangle;

public interface Rectangular {

  int x();

  int y();

  int width();

  int height();

  default Rectangle asRectangle() {
    return new Rectangle(x(), y(), width(), height());
  }

  default boolean intersects(Rectangular other) {
    return this.asRectangle().intersects(other.asRectangle());
  }
}
