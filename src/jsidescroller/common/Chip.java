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
  private final int size;

  public Chip(Stage stage, int size) {
    this.stage = Objects.requireNonNull(stage);
    if (size <= 0) {
      throw new IllegalArgumentException("size:" + size);
    }
    this.size = size;
  }

  @Override
  public Stage getStage() {
    return stage;
  }

  @Override
  public final int width() {
    return size;
  }

  @Override
  public final int height() {
    return size;
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
