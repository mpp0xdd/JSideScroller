package jsidescroller.common;

import java.util.List;
import jsidescroller.common.interfaces.Accelerable;
import jsidescroller.common.interfaces.Jumpable;
import jsidescroller.common.interfaces.Movable;
import jsidescroller.common.interfaces.Weighable;

public abstract class Player extends Chip implements Accelerable, Jumpable, Movable, Weighable {

  public Player(Stage stage, int size) {
    super(stage, size);
  }

  @Override
  public final Type type() {
    return Type.PLAYER;
  }

  public void collectCoins() {
    List<Coin> coins =
        getStage().coins().stream().filter(this::intersects).filter(Coin::isNotTaken).toList();

    coins.forEach(
        coin -> {
          coin.take();
          getStage().remove(coin);
        });
  }

  public void collectItems() {
    List<Item> items =
        getStage().items().stream().filter(this::intersects).filter(Item::isNotTaken).toList();

    items.forEach(
        item -> {
          item.take();
          getStage().remove(item);
        });
  }
}
