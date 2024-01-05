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
     * Returns the snake.
     *
     * @return the snake
     * @apiNote be careful when using this method,
     * as it returns a reference to the original snake.
     * Any changes made to the snake will affect the original snake.
     */
    Snake getSnake();

    /**
     * Moves the player in the specified direction.
     *
     * @param direction the direction in which the player should move
     */
    void moveToDirection(Direction direction);

}
