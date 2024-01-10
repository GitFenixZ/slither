package model;

import javafx.geometry.Point2D;
import model.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

public class TestFood {

    private GridModel grid;

    @BeforeEach
    void setUp() {
        grid = spy(GridModel.class);
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
