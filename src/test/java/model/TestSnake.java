package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSnake {

    /**
     * Move one step to the right / Expected coordinates : (1, 0)
     */
    @Test
    void testMoveToPositionOneRight() {
        Snake snake = new Snake(0, 0);
        snake.moveToPosition(1, 0);
        assertEquals(1, snake.getHeadX());
        assertEquals(0, snake.getHeadY());
    }

    /**
     * Move one step to the left / Expected coordinates : (-1, 0)
     */
    @Test
    void testMoveToPositionOneLeft() {
        Snake snake = new Snake(0, 0);
        snake.moveToPosition(-1, 0);
        assertEquals(-1, snake.getHeadX());
        assertEquals(0, snake.getHeadY());
    }

    /**
     * Move one step up / Expected coordinates : (0, -1)
     */
    @Test
    void testMoveToPositionOneUp() {
        Snake snake = new Snake(0, 0);
        snake.moveToPosition(0, -1);
        assertEquals(0, snake.getHeadX());
        assertEquals(-1, snake.getHeadY());
    }

    /**
     * Move one step down / Expected coordinates : (0, 1)
     */
    @Test
    void testMoveToPositionOneDown() {
        Snake snake = new Snake(0, 0);
        snake.moveToPosition(0, 1);
        assertEquals(0, snake.getHeadX());
        assertEquals(1, snake.getHeadY());
    }

    /**
     * Move following a certain list of moves / Expected coordinates : (2, -2)
     */
    @Test
    void testMoveToPositionCombination1() {
        Snake snake = new Snake(0, 0);
        snake.moveToPosition(1, 0);
        snake.moveToPosition(2, 0);
        snake.moveToPosition(2, -1);
        snake.moveToPosition(2, -2);
        assertEquals(2, snake.getHeadX());
        assertEquals(-2, snake.getHeadY());
    }

    /**
     * Move following a certain list of moves / Expected coordinates : (0, 0)
     */
    @Test
    void testMoveToPositionCombination2() {
        Snake snake = new Snake(0, 0);
        snake.moveToPosition(1, 0);
        snake.moveToPosition(2, 0);
        snake.moveToPosition(3, 0);
        snake.moveToPosition(3, 1);
        snake.moveToPosition(2, 1);
        snake.moveToPosition(2, 0);
        snake.moveToPosition(2, -1);
        snake.moveToPosition(1, -1);
        snake.moveToPosition(1, 0);
        snake.moveToPosition(0, 0);
        assertEquals(0, snake.getHeadX());
        assertEquals(0, snake.getHeadY());
    }
}
