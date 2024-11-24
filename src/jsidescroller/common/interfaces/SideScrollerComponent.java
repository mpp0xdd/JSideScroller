package jsidescroller.common.interfaces;

import jglib.util.spec.Drawable;
import jglib.util.spec.Rectangular;
import jsidescroller.common.Stage;

public interface SideScrollerComponent extends Drawable, Rectangular, Locatable {

  Stage getStage();
}
