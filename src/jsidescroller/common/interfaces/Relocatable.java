package jsidescroller.common.interfaces;

import java.awt.Point;

public interface Relocatable extends Locatable {

  void setLocation(Point location);

  void setLocation(int x, int y);
}
