package jsidescroller.common;

import java.util.Objects;
import jsidescroller.common.interfaces.SideScrollerComponent;

public abstract class Chip implements SideScrollerComponent {

  public enum Type {
    PLAYER,
    BLOCK,
    ITEM_BLOCK,
    ITEM,
    COIN,
    ENEMY,
    VOID,
  }

  private final Stage stage;

  public Chip(Stage stage) {
    this.stage = Objects.requireNonNull(stage);
  }

  @Override
  public Stage getStage() {
    return stage;
  }

  public final int size() {
    return stage.chipSize();
  }

  @Override
  public final int width() {
    return stage.chipSize();
  }

  @Override
  public final int height() {
    return stage.chipSize();
  }

  public final boolean isPlayer() {
    return type().equals(Type.PLAYER);
  }

  public final boolean isBlock() {
    return type().equals(Type.BLOCK);
  }

  public final boolean isItemBlock() {
    return type().equals(Type.ITEM_BLOCK);
  }

  public final boolean isItem() {
    return type().equals(Type.ITEM);
  }

  public final boolean isCoin() {
    return type().equals(Type.COIN);
  }

  public final boolean isEnemy() {
    return type().equals(Type.ENEMY);
  }

  public final boolean isVoid() {
    return type().equals(Type.VOID);
  }

  public abstract Type type();
}
