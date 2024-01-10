package model;

import javafx.geometry.Point2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

class TestIsInsideGrid {

    private GridModel grid;

    @BeforeEach
    void setUp() {
        grid = spy(GridModel.class);
        doReturn(25).when(grid).getHeight();
        doReturn(25).when(grid).getWidth();
    }

    @Test
    void isInsideGrid_Valid() {
        for (int row = 0; row < grid.getHeight(); row++) {
            for (int col = 0; col < grid.getWidth(); col++) {
                Point2D coordinates = new Point2D(row, col);
                assertTrue(grid.isInsideGrid(coordinates));
            }
        }
    }

    @Test
    void isInsideGrid_Invalid() {
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

}
