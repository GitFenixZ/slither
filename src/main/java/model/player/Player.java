package model.player;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import model.Direction;
import model.Segment;
import model.Snake;

import java.util.List;

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

    /**
     * Returns the Builder of the player.
     *
     * @return the builder of the player
     * @apiNote This method is here to force the implementation of {@link PlayerBuilder}
     * in subclasses.
     */
    PlayerBuilder<? extends PlayerImplementation> getBuilder();

    default void grow() {
        if (getSnake() == null) {
            return;
        }

        getSnake().grow();
    }

    default void extractCoordinates(List<Point2D> destination) {
        if (destination == null || getSnake() == null || getSnake().getSegments() == null) {
            return;
        }

        destination.addAll(
                getSnake().getSegments().stream().map(
                        Segment::getCoordinates
                ).toList()
        );
    }
}
