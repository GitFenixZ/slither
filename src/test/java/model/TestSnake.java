package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSnake {

    /**
     * Move one step to the right / Expected coordinates : (1, 0)
     */
    @Test
    void moveToDirection_OneRight() {
        Snake snake = new Snake.Builder().build();
        snake.moveToDirection(Direction.RIGHT);
        assertEquals(1, snake.getHeadX());
        assertEquals(0, snake.getHeadY());
    }

    /**
     * Move one step to the left / Expected coordinates : (-1, 0)
     */
    @Test
    void moveToDirection_OneLeft() {
        Snake snake = new Snake.Builder().build();
        snake.moveToDirection(Direction.LEFT);
        assertEquals(-1, snake.getHeadX());
        assertEquals(0, snake.getHeadY());
    }

    /**
     * Move one step up / Expected coordinates : (0, -1)
     */
    @Test
    void moveToDirection_OneUp() {
        Snake snake = new Snake.Builder().build();
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
        Snake snake = new Snake.Builder().build();
        snake.moveToDirection(Direction.DOWN);
        assertEquals(0, snake.getHeadX());
        assertEquals(1, snake.getHeadY());
    }

    /**
     * Move following a certain list of moves / Expected coordinates : (2, -2)
     */
    @Test
    void moveToDirection_Combination() {
        Snake snake = new Snake.Builder().build();
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
}
