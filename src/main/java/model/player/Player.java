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

    default void grow() {
        getSnake().grow();
    }

    default boolean isOnCoordinates(Point2D coordinates) {
        return getSnake().getSegments().stream().anyMatch(
                (Segment segment) -> segment.getCoordinates().equals(coordinates));
    }

    default void extractCoordinates(List<Point2D> destination) {
        destination.addAll(
                getSnake().getSegments().stream().map(
                        Segment::getCoordinates
                ).toList()
        );
    }
}
