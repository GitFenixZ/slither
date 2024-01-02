package model.player;

import javafx.scene.paint.Color;
import model.Direction;
import model.Snake;

public interface Player {

    /**
     * Returns the color of the player.
     *
     * @return the color of the player
     */
    Color getColor();

    /**
     * Returns the name of the player.
     *
     * @return the name of the player
     */
    String getName();

    /**
     * Returns a deep copy of the snake.
     *
     * @return a deep copy of the snake
     */
    Snake getSnake();

    /**
     * Moves the player in the specified direction.
     *
     * @param direction the direction in which the player should move
     */
    void moveToDirection(Direction direction);

}
