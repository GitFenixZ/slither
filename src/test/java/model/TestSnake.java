package model;

import javafx.geometry.Point2D;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    void getDangerZone_HorizontalSnake() {
        Snake snake = new Snake.Builder().coordinates(Point2D.ZERO).build();

        List<Point2D> danger = snake.getDangerZone();

        List<Point2D> expected = new ArrayList<>(Arrays.asList(new Point2D(-1, 0),
                new Point2D(0, -1),
                new Point2D(0, 1),
                new Point2D(1, 0),
                new Point2D(0, 0)
        ));

        assertEquals(expected.size(), danger.size());
        for (int i = 0; i < danger.size(); i++) {
            assertEquals(expected.get(i), danger.get(i));
        }
    }

    @Test
    void getDangerZone_LSnake() {
        Snake snake = new Snake.Builder().coordinates(Point2D.ZERO).build();
        snake.moveToDirection(Direction.UP);

        List<Point2D> danger = snake.getDangerZone();

        List<Point2D> expected = new ArrayList<>(Arrays.asList(new Point2D(-1, -1),
                new Point2D(0, -2),
                new Point2D(0, 0),
                new Point2D(1, -1),
                new Point2D(0, -1)
        ));

        assertEquals(expected.size(), danger.size());
        for (int i = 0; i < danger.size(); i++) {
            assertEquals(expected.get(i), danger.get(i));
        }
    }

    @Test
    void getDangerZone_VerticalSnake() {
        Snake snake = new Snake.Builder().coordinates(Point2D.ZERO).build();
        snake.moveToDirection(Direction.UP);
        snake.moveToDirection(Direction.UP);

        List<Point2D> danger = snake.getDangerZone();

        List<Point2D> expected = new ArrayList<>(Arrays.asList(new Point2D(-1, -2),
                new Point2D(0, -3),
                new Point2D(0, -1),
                new Point2D(1, -2),
                new Point2D(0, -2)
        ));

        assertEquals(expected.size(), danger.size());
        for (int i = 0; i < danger.size(); i++) {
            assertEquals(expected.get(i), danger.get(i));
        }
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
