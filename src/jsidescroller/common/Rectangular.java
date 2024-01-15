package jsidescroller.common;

import java.awt.Rectangle;

public interface Rectangular {

  int x();

  int y();

  int width();

  int height();

  default Rectangle asRectangle() {
    return new Rectangle(x(), y(), width(), height());
  }
}
