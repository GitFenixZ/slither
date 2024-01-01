package model.player;

import javafx.scene.paint.Color;
import model.Snake;

public interface Player {

    Color getColor();

    String getName();

    Snake getSnake();

}
