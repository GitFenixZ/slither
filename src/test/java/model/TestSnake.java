package model;

import javafx.geometry.Point2D;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSnake {

    /**
     * Move one step to the right / Expected coordinates : (1, 0)
     */
    @Test
    void moveToDirection_OneRight() {
        Snake snake = new Snake.Builder().coordinates(Point2D.ZERO).build();
        snake.moveToDirection(Direction.RIGHT);
        assertEquals(1, snake.getHeadX());
        assertEquals(0, snake.getHeadY());
    }

    /**
     * Move one step to the left / Expected coordinates : (-1, 0)
     */
    @Test
    void moveToDirection_OneLeft() {
        Snake snake = new Snake.Builder().coordinates(Point2D.ZERO).build();
        snake.moveToDirection(Direction.LEFT);
        assertEquals(-1, snake.getHeadX());
        assertEquals(0, snake.getHeadY());
    }

    /**
     * Move one step up / Expected coordinates : (0, -1)
     */
    @Test
    void moveToDirection_OneUp() {
        Snake snake = new Snake.Builder().coordinates(Point2D.ZERO).build();
        snake.moveToDirection(Direction.UP
        );
        assertEquals(0, snake.getHeadX());
        assertEquals(-1, snake.getHeadY());
    }

    /**
     * Move one step down / Expected coordinates : (0, 1)
     */
    @Test
    void moveToDirection_OneDown() {
        Snake snake = new Snake.Builder().coordinates(Point2D.ZERO).build();
        snake.moveToDirection(Direction.DOWN);
        assertEquals(0, snake.getHeadX());
        assertEquals(1, snake.getHeadY());
    }

    /**
     * Move following a certain list of moves / Expected coordinates : (2, -2)
     */
    @Test
    void moveToDirection_Combination() {
        Snake snake = new Snake.Builder().coordinates(Point2D.ZERO).build();
        snake.moveToDirection(Direction.UP);
        snake.moveToDirection(Direction.DOWN);
        snake.moveToDirection(Direction.LEFT);
        snake.moveToDirection(Direction.RIGHT);
        snake.moveToDirection(Direction.RIGHT);
        snake.moveToDirection(Direction.RIGHT);
        snake.moveToDirection(Direction.UP);
        snake.moveToDirection(Direction.UP);
        assertEquals(2, snake.getHeadX());
        assertEquals(-2, snake.getHeadY());
    }

    @Test
    void grow() {
        Snake snake = new Snake.Builder().coordinates(Point2D.ZERO).build();

        int old_length;
        Segment old_tail;
        for (int i = 0; i < 10; i++) {
            old_tail = snake.getTail();
            old_length = snake.getLength();

            snake.grow();

            assertEquals(old_length + 1, snake.getLength());
            assertEquals(old_tail.getDirection(), snake.getTail().getDirection());
            assertEquals(
                    old_tail.getCoordinates().add(
                            old_tail.getDirection().
                                    getOppositeDirection().
                                    getVectorOfDirection()
                    )
                    , snake.getTail().getCoordinates());
        }
    }
}
