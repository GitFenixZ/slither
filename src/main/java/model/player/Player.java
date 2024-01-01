package model.player;

import javafx.scene.paint.Color;
import model.Direction;
import model.Snake;

public interface Player {

    Color getColor();

    String getName();

    /**
     * Returns a copy of the snake.
     *
     * @return a copy of the snake
     */
    Snake getSnake();

    void moveToDirection(Direction direction);

}
