package model;

import javafx.geometry.Point2D;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.spy;

class TestGrid {
    @Test
    void isInsideGrid_Valid() {
        GridModel grid = spy(new TestGridModel());
        for (int row = 0; row < grid.getHeight(); row++) {
            for (int col = 0; col < grid.getWidth(); col++) {
                Point2D coordinates = new Point2D(row, col);
                assertTrue(grid.isInsideGrid(coordinates));
            }
        }
    }

    @Test
    void isInsideGrid_Invalid() {
        GridModel grid = spy(new TestGridModel());
        for (int row = -10; row <= grid.getHeight() + 10; row++) {
            for (int col = -10; col <= grid.getWidth() + 10; col++) {
                if (row >= 0 && row < grid.getHeight() && col >= 0 && col < grid.getWidth()) {
                    continue;
                }
                Point2D coordinates = new Point2D(row, col);
                assertFalse(grid.isInsideGrid(coordinates));
            }
        }
    }

    @Test
    void isMoveValid_Valid() {
        GridModel grid = spy(new TestGridModel());
        Snake snake = new Snake(new Point2D(5, 5));
        for (Direction direction : Direction.values()) {
            assertTrue(grid.isMoveValid(snake, direction));
        }
    }

    @Test
    void isMoveValid_Invalid() {
        GridModel grid = spy(new TestGridModel());
        Snake snake = new Snake(new Point2D(0, 0));
        assertFalse(grid.isMoveValid(snake, Direction.LEFT));
        assertFalse(grid.isMoveValid(snake, Direction.UP));

        snake = new Snake(new Point2D(grid.getWidth() - 1, grid.getHeight() - 1));
        assertFalse(grid.isMoveValid(snake, Direction.RIGHT));
        assertFalse(grid.isMoveValid(snake, Direction.DOWN));
    }

    private static class TestGridModel implements GridModel {

        private static final int WIDTH = 25;
        private static final int HEIGHT = 25;

        @Override
        public int getWidth() {
            return WIDTH;
        }

        @Override
        public int getHeight() {
            return HEIGHT;
        }
    }
}
