package model.player;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import model.Direction;
import model.Segment;
import model.Snake;

import java.util.ArrayList;
import java.util.List;

public interface Player {

    /**
     * Returns the player's id
     *
     * @return the player's id
     */
    int getId();

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
     * Get the list of positions where an opponent might collide with this object next turn
     *
     * @return the list of positions where an opponent might collide with this object next turn
     */
    default List<Point2D> getDangerZone() {
        List<Point2D> dangerZone = new ArrayList<>();
        Snake snake = getSnake();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if ((Math.abs(i) == 1 && j == 0) || (i == 0 && Math.abs(j) == 1)) {
                    dangerZone.add(new Point2D(snake.getHeadX() + i, snake.getHeadY() + j));
                }
            }
        }

        for (int i = 1; i < snake.getSegments().size() - 1; i++) {
            Point2D cell = snake.getSegmentAtIndex(i).getCoordinates();
            if (!dangerZone.contains(cell)) {
                dangerZone.add(cell);
            }
        }

        return dangerZone;
    }

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
