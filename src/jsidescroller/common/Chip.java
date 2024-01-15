package jsidescroller.common;

public interface Chip extends SideScrollerComponent {

  @Override
  default int width() {
    return 32;
  }

  @Override
  default int height() {
    return 32;
  }
}
