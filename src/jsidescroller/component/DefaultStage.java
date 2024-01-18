package jsidescroller.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import jsidescroller.common.Chip;
import jsidescroller.common.Player;
import jsidescroller.common.Stage;

public class DefaultStage extends Stage {

  @Override
  public void draw(Graphics g) {
    stage().forEach(chip -> chip.draw(g));
    player().draw(g);
  }

  @Override
  public int width() {
    return 640;
  }

  @Override
  public int height() {
    return 480;
  }

  @Override
  public int gravity() {
    return 1;
  }

  @Override
  public void provideGravity() {
    player().accept(this);
  }

  @Override
  protected List<Chip> newStage() {
    final int rows = 15;
    final int columns = 20;
    final int chipSize = ColorChip.SIZE;

    List<Chip> stage = new ArrayList<>();
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        Point location = new Point(j * chipSize, i * chipSize);
        Chip chip = new ColorChip(this, location, Color.BLACK);
        stage.add(chip);
      }
    }

    return Collections.unmodifiableList(stage);
  }

  @Override
  protected Player newPlayer() {
    return new DefaultPlayer(this);
  }
}
