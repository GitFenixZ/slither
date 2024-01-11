package model.food;

import javafx.geometry.Point2D;
import model.GridModel;

import java.util.List;

public class Food {

    private final GridModel grid;
    private Point2D coordinates = null; // null means that the food has been deleted

    /**
     * Creates a new food.
     *
     * @param grid the grid
     * @apiNote This constructor uses {@link #spawn()} to spawn the initial food.
     */
    public Food(GridModel grid) {
        this.grid = grid;
        spawn(); // DO NOT REMOVE: initial spawn the food
    }

    /**
     * Spawns a new food.
     *
     * @return true if the food has been deleted; false otherwise
     * @apiNote Use this method after calling {@link #delete()}.
     */
    public boolean spawn() {
        if (coordinates != null) {
            return false;
        }

        List<Point2D> free_coordinates = grid.getFreeCoordinates();

        if (free_coordinates.isEmpty()) {
            return false;
        }

        // NOTE: Maybe use a random number generator with a random seed
        coordinates = free_coordinates.get(
                (int) (Math.random() * free_coordinates.size())
        );
        return true;
    }

    /**
     * Delete the food.
     *
     * @return true if the food has been deleted; false otherwise
     * @apiNote Use this method before calling {@link #spawn()}.
     */
    public boolean delete() {
        if (coordinates == null) {
            return false;
        }

        coordinates = null;
        return true;
    }

    public Point2D getCoordinates() {
        if (coordinates == null) {
            return null;
        }

        return new Point2D(coordinates.getX(), coordinates.getY());
    }
}
