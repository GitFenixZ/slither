package model;

import javafx.geometry.Point2D;
import model.food.Food;
import model.grid.GridModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class TestFood {

    private Food food;
    private GridModel grid;

    private List<Point2D> generateFreeCoordinates(int width, int height) {
        doReturn(width).when(grid).getWidth();
        doReturn(height).when(grid).getHeight();

        List<Point2D> free_coordinates = new ArrayList<>();

        for (int row = 0; row < grid.getHeight(); row++) {
            for (int col = 0; col < grid.getWidth(); col++) {
                free_coordinates.add(new Point2D(col, row));
            }
        }

        return free_coordinates;
    }

    @BeforeEach
    void setUp() {
        grid = mock(GridModel.class);
    }

    @Test
    void spawn_doesNothing_whenCoordinatesHasBeenSet() {
        doReturn(generateFreeCoordinates(10, 10)).when(grid).getFreeCoordinates();

        food = new Food(grid);

        Point2D before = food.getCoordinates();

        assertNotNull(before);
        assertFalse(food.spawn());
        assertEquals(before, food.getCoordinates());
    }

    @Test
    void spawn_doesNothing_whenNoFreeCoordinates() {
        doReturn(new ArrayList<Point2D>()).when(grid).getFreeCoordinates();

        food = new Food(grid);

        assertNull(food.getCoordinates());
        assertFalse(food.spawn());
        assertNull(food.getCoordinates());
    }

    @Test
    void spawn_doesSpawn_whenCoordinatesUnsetAndFreeCoordinatesAvailable() {
        doReturn(generateFreeCoordinates(10, 10)).when(grid).getFreeCoordinates();

        food = new Food(grid);
        food.delete();

        assertNull(food.getCoordinates());
        assertTrue(food.spawn());
        assertNotNull(food.getCoordinates());
    }

    @Test
    void delete_doesNothing_whenCoordinatesHasNotBeenSet() {
        food = new Food(grid);

        assertNull(food.getCoordinates());
        assertFalse(food.delete());
        assertNull(food.getCoordinates());
    }

    @Test
    void delete_doesDelete_whenCoordinatesHasBeenSet() {
        doReturn(generateFreeCoordinates(10, 10)).when(grid).getFreeCoordinates();

        food = new Food(grid);

        assertNotNull(food.getCoordinates());
        assertTrue(food.delete());
        assertNull(food.getCoordinates());
    }

    @Test
    void getCoordinates_returnsNull_whenCoordinatesHasNotBeenSet() {
        food = new Food(grid);

        assertNull(food.getCoordinates());
    }

    @Test
    void getCoordinates_returnsNonNull_whenCoordinatesHasNotBeenSet() {
        doReturn(generateFreeCoordinates(10, 10)).when(grid).getFreeCoordinates();

        food = new Food(grid);

        assertNotNull(food.getCoordinates());
    }

}
