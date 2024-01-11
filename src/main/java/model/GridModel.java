package model;

import javafx.geometry.Point2D;
import model.food.Food;
import model.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface representing a grid in a slither game.
 */
public interface GridModel {

    /**
     * Gets the human player of the grid.
     *
     * @return the player of the grid
     */
    Player getHumanPlayer();

    /**
     * Gets the computer-controlled player of the grid.
     *
     * @return the player of the grid
     */
    Player getComputerPlayer();

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
     * Checks if the grid is full.
     * The grid is full when there are no more free coordinates.
     *
     * @return true if the grid is full, false otherwise
     */
    default boolean isFull() {
        return getFreeCoordinates().size() == getHeight() * getWidth();
    }

    /**
     * Gets the free coordinates of the grid.
     * Coordinates is free if there is no snake and no food in it.
     *
     * @return the free coordinates of the grid
     */
    default List<Point2D> getFreeCoordinates() {
        List<Point2D> non_free_coordinates = new ArrayList<>();

        // Add coordinates occupied by players' whole snake to non_free_coordinates
        getHumanPlayer().extractCoordinates(non_free_coordinates);
        getComputerPlayer().extractCoordinates(non_free_coordinates);

        // Add coordinates occupied by food to non_free_coordinates
        if (getFood() != null && getFood().getCoordinates() != null) {
            non_free_coordinates.add(getFoodCoordinates());
        }

        List<Point2D> free_coordinates = new ArrayList<>();

        for (int row = 0; row < getHeight(); row++) {
            for (int column = 0; column < getWidth(); column++) {
                Point2D coordinates = new Point2D(column, row);
                free_coordinates.add(coordinates);
            }
        }

        free_coordinates.removeAll(non_free_coordinates);

        return free_coordinates;
    }

    default boolean movePlayer(Player player, Direction direction) {
        if (!isMoveValid(player, direction)) {
            return false;
        }

        player.moveToDirection(direction);
        handleFood(player);

        return true;
    }

    default void handleFood(Player player) {
        if (!isFoodEaten(player)) {
            return;
        }

        deleteFood();
        player.grow();
        spawnFood();
    }

    default void deleteFood() {
        if (getFood() == null) {
            return;
        }

        getFood().delete();
    }

    default void spawnFood() {
        if (getFood() == null) {
            return;
        }

        getFood().spawn();
    }

    /**
     * Checks if the food has been eaten by a player.
     *
     * @param player the player to check
     * @return true if the food has been eaten by the player, false otherwise
     */
    default boolean isFoodEaten(Player player) {
        return getFoodCoordinates().equals(player.getSnake().getHeadCoordinates());
    }

    /**
     * Gets a copy of the grid's food's coordinates.
     *
     * @return a copy of the grid's food's coordinates
     */
    default Point2D getFoodCoordinates() {
        return getFood().getCoordinates();
    }

    /**
     * Gets the food of the grid.
     *
     * @return the <b>ORIGINAL</b> pointer to the food of the grid if it exists,
     * null otherwise
     */
    Food getFood();


    /**
     * Checks if a move in a given direction is valid for a player.
     *
     * @param player    the player to check
     * @param direction the direction of the move
     * @return true if the move is valid, false otherwise
     */
    default boolean isMoveValid(Player player, Direction direction) {
        return true;
    }

    /**
     * Checks if the game is over.
     *
     * @return true if the game is over, false otherwise.
     * @apiNote The game is considered over if the snake's head is outside the grid.
     */
    default boolean isGameOver() {
        return !isInsideGrid(getHumanPlayer().
                getSnake().
                getHeadCoordinates());
    }

}
