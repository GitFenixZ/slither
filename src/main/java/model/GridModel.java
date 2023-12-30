package model;

import javafx.geometry.Point2D;

/**
 * Interface representing a grid in a slither game.
 */
public interface GridModel {

    /**
     * Gets the width of the grid.
     *
     * @return the width of the grid
     */
    int getWidth();

    /**
     * Gets the height of the grid.
     *
     * @return the height of the grid
     */
    int getHeight();

    /**
     * Checks if the given coordinates is inside the grid.
     *
     * @param coordinates the coordinates to check
     * @return true if the coordinates is inside the grid, false otherwise
     */
    default boolean isInsideGrid(Point2D coordinates) {
        return coordinates.getX() >= 0 && coordinates.getX() < getWidth() &&
                coordinates.getY() >= 0 && coordinates.getY() < getHeight();
    }

    /**
     * Checks if a move in a given direction is valid for a snake.
     *
     * @param snake     the snake to check
     * @param direction the direction of the move
     * @return true if the move is valid, false otherwise
     */
    default boolean isMoveValid(Snake snake, Direction direction) {
        Point2D destination = snake.getCoordinates().add(direction.getVectorOfDirection());
        return isInsideGrid(destination);
    }

}
