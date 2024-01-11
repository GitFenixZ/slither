package model;

import javafx.geometry.Point2D;
import model.food.Food;
import model.player.ComputerPlayerImplementation;
import model.player.HumanPlayerImplementation;
import model.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
        // Set up stubbing
        doReturn(10).when(grid).getHeight();
        doReturn(10).when(grid).getWidth();
        Player player = spy(Player.class);

        // Set up assertions and expectations
        Point2D food_coordinates;
        for (int row = 0; row < grid.getHeight(); row++) {
            for (int col = 0; col < grid.getWidth(); col++) {
                food_coordinates = new Point2D(col, row);
                doReturn(food_coordinates).when(grid).getFoodCoordinates();
                doReturn(new Snake.Builder().coordinates(food_coordinates).build()).when(player).getSnake();
                assertTrue(grid.isFoodEaten(player));
            }
        }
    }

    @Test
    void isFoodEaten_Invalid() {
        // Set up stubbing
        doReturn(10).when(grid).getHeight();
        doReturn(10).when(grid).getWidth();
        Player player = spy(Player.class);

        // Set up assertions and expectations
        Point2D snake_coordinates;
        Point2D food_coordinates = Point2D.ZERO;
        doReturn(food_coordinates).when(grid).getFoodCoordinates();

        for (int row = 0; row < grid.getHeight(); row++) {
            for (int col = 0; col < grid.getWidth(); col++) {
                // Skip the food coordinates
                if (col == 0 && row == 0) {
                    continue;
                }

                snake_coordinates = new Point2D(col, row);
                doReturn(new Snake.Builder().coordinates(snake_coordinates).build()).when(player).getSnake();
                assertFalse(grid.isFoodEaten(player));
            }
        }
    }

    @Test
    void isFull_EmptyGrid() {
        doReturn(10).when(grid).getHeight();
        doReturn(10).when(grid).getWidth();
        doReturn(new ArrayList<Point2D>()).when(grid).getFreeCoordinates();

        assertFalse(grid.isFull());
    }

    @Test
    void isFull_FullGrid() {
        doReturn(10).when(grid).getHeight();
        doReturn(10).when(grid).getWidth();

        List<Point2D> free_coordinates = new ArrayList<>();
        for (int row = 0; row < grid.getHeight(); row++) {
            for (int col = 0; col < grid.getWidth(); col++) {
                free_coordinates.add(new Point2D(col, row));
            }
        }

        doReturn(free_coordinates).when(grid).getFreeCoordinates();

        assertTrue(grid.isFull());
    }

    @Test
    void getFreeCoordinates_EmptyGrid() {
        // Set up stubbing
        doReturn(10).when(grid).getHeight();
        doReturn(10).when(grid).getWidth();

        Player human = spy(new HumanPlayerImplementation.Builder().build());
        Player computer = spy(new ComputerPlayerImplementation.Builder().build());

        doReturn(human).when(grid).getHumanPlayer();
        doReturn(computer).when(grid).getComputerPlayer();

        doNothing().when(human).extractCoordinates(anyList());
        doNothing().when(computer).extractCoordinates(anyList());
        doReturn(null).when(grid).getFood();

        // Set up expected
        List<Point2D> expected = new ArrayList<>();
        for (int row = 0; row < grid.getHeight(); row++) {
            for (int col = 0; col < grid.getWidth(); col++) {
                expected.add(new Point2D(col, row));
            }
        }

        List<Point2D> actual = grid.getFreeCoordinates();

        // When there is nothing on the grid, all coordinates should be free
        assertEquals(grid.getHeight() * grid.getWidth(), actual.size());
        assertIterableEquals(expected, actual);
    }

    @Test
    void getFreeCoordinates_GridHasElements() {
        // Set up stubbing
        doReturn(10).when(grid).getHeight();
        doReturn(10).when(grid).getWidth();

        Player human = spy(new HumanPlayerImplementation.Builder().build());
        Player computer = spy(new ComputerPlayerImplementation.Builder().build());

        doReturn(human).when(grid).getHumanPlayer();
        doReturn(computer).when(grid).getComputerPlayer();

        Food food = mock(Food.class);
        Point2D food_coordinates = new Point2D(0, 0);
        doReturn(food).when(grid).getFood();
        doReturn(food_coordinates).when(food).getCoordinates();

        // Set up expected
        List<Point2D> expected = new ArrayList<>();
        for (int row = 0; row < grid.getHeight(); row++) {
            for (int col = 0; col < grid.getWidth(); col++) {
                expected.add(new Point2D(col, row));
            }
        }

        List<Point2D> toRemove = new ArrayList<>();
        human.extractCoordinates(toRemove);
        computer.extractCoordinates(toRemove);
        toRemove.add(food_coordinates);

        expected.removeAll(toRemove);

        List<Point2D> actual = grid.getFreeCoordinates();

        assertEquals(expected.size(), actual.size());
        assertIterableEquals(expected, actual);
    }

}