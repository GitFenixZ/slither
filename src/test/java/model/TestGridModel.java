package model;

import javafx.geometry.Point2D;
import model.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class TestGridModel {

    private GridModel grid;

    @BeforeEach
    void setUp() {
        grid = spy(GridModel.class);
    }

    @Test
    void isInsideGrid_Valid() {
        when(grid.getHeight()).thenReturn(25);
        when(grid.getWidth()).thenReturn(25);
        for (int row = 0; row < grid.getHeight(); row++) {
            for (int col = 0; col < grid.getWidth(); col++) {
                Point2D coordinates = new Point2D(row, col);
                assertTrue(grid.isInsideGrid(coordinates));
            }
        }
    }

    @Test
    void isInsideGrid_Invalid() {
        when(grid.getHeight()).thenReturn(25);
        when(grid.getWidth()).thenReturn(25);
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
    void isFoodEaten_Valid() {
        doReturn(new Point2D(16, 16)).when(grid).getFoodCoordinates();

        Player player = spy(Player.class);
        doReturn(new Snake.Builder().coordinates(new Point2D(16, 16)).build()).when(player).getSnake();

        assertTrue(grid.isFoodEaten(player));
    }

    @Test
    void isFoodEaten_Invalid() {
        doReturn(new Point2D(16, 16)).when(grid).getFoodCoordinates();

        Player player = spy(Player.class);
        doReturn(new Snake.Builder().coordinates(new Point2D(0, 0)).build()).when(player).getSnake();

        assertFalse(grid.isFoodEaten(player));
    }

}
