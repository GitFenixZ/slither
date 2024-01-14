package model;

import javafx.geometry.Point2D;
import model.food.Food;
import model.player.ComputerPlayerImplementation;
import model.player.HumanPlayerImplementation;
import model.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

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
    void getFreeCoordinates_GridHasSomeElements() {
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

    @Test
    void getFreeCoordinates_GridIsFull() {
        // Set up stubbing
        doReturn(10).when(grid).getHeight();
        doReturn(10).when(grid).getWidth();

        // For the sake of simplicity, we will assume that the grid is full of a human's snake
        List<Segment> human_snake_segments = new ArrayList<>();

        for (int row = 0; row < grid.getHeight(); row++) {
            for (int col = 0; col < grid.getWidth(); col++) {
                // Set up human_snake
                human_snake_segments.add(
                        new BasicSegment(new Point2D(col, row), Direction.RIGHT)
                );
            }
        }

        Snake human_snake = new Snake.Builder().segments(human_snake_segments).build();
        Player human = spy(new HumanPlayerImplementation.Builder().snake(human_snake).build());
        Player computer = spy(new ComputerPlayerImplementation.Builder().build());

        doReturn(human).when(grid).getHumanPlayer();
        doReturn(computer).when(grid).getComputerPlayer();
        doNothing().when(computer).extractCoordinates(anyList());

        doReturn(null).when(grid).getFood();

        List<Point2D> actual = grid.getFreeCoordinates();

        assertEquals(0, actual.size());
    }

    @Test
    void movePlayer_Behaviour_InOrder_whenMoveIsInvalid() {
        Player player = mock(Player.class);
        Direction direction = Direction.UP;

        doReturn(false).when(grid).isMoveValid(player, direction);

        assertFalse(grid.movePlayer(player, direction));

        InOrder inorder = inOrder(grid, player);
        inorder.verify(grid, times(1)).isMoveValid(player, direction);
        inorder.verify(player, never()).moveToDirection(direction);
        inorder.verify(grid, never()).handleFood(player);
    }

    @Test
    void movePlayer_Behaviour_InOrder_whenMoveIsValid() {
        Player player = mock(Player.class);
        Direction direction = Direction.UP;

        doReturn(true).when(grid).isMoveValid(player, direction);
        doNothing().when(player).moveToDirection(direction);
        doNothing().when(grid).handleFood(player);

        assertTrue(grid.movePlayer(player, direction));

        InOrder inorder = inOrder(grid, player);
        inorder.verify(grid, times(1)).isMoveValid(player, direction);
        inorder.verify(player, times(1)).moveToDirection(direction);
        inorder.verify(grid, times(1)).handleFood(player);
    }

    @Test
    void deleteFood_doesNothing_whenGetFoodReturnsNull() {
        Food food = mock(Food.class);
        doReturn(null).when(grid).getFood();

        grid.deleteFood();
        verify(food, never()).delete();
    }

    @Test
    void deletedFood_callsDelete_whenGetFoodReturnsNonNull() {
        Food food = mock(Food.class);
        doReturn(food).when(grid).getFood();

        grid.deleteFood();
        verify(food, times(1)).delete();
    }

    @Test
    void spawnFood_doesNothing_whenGetFoodReturnsNull() {
        Food food = mock(Food.class);
        doReturn(null).when(grid).getFood();

        grid.spawnFood();
        verify(food, never()).spawn();
    }

    @Test
    void spawnFood_callsSpawn_whenGetFoodReturnsNonNull() {
        Food food = mock(Food.class);
        doReturn(food).when(grid).getFood();

        grid.spawnFood();
        verify(food, times(1)).spawn();
    }

    @Test
    void handleFood_Behaviour_InOrder_whenFoodNotEaten() {
        Player player = mock(Player.class);

        doReturn(false).when(grid).isFoodEaten(player);

        grid.handleFood(player);

        InOrder inorder = inOrder(grid, player);

        inorder.verify(grid, times(1)).isFoodEaten(player);
        inorder.verify(grid, times(0)).deleteFood();
        inorder.verify(player, never()).grow();
        inorder.verify(grid, times(0)).spawnFood();
    }

    @Test
    void handleFood_Behaviour_InOrder_whenFoodEaten() {
        Player player = mock(Player.class);

        doReturn(true).when(grid).isFoodEaten(player);

        grid.handleFood(player);

        InOrder inorder = inOrder(grid, player);

        inorder.verify(grid, times(1)).isFoodEaten(player);
        inorder.verify(grid, times(1)).deleteFood();
        inorder.verify(player, times(1)).grow();
        inorder.verify(grid, times(1)).spawnFood();
    }

}
