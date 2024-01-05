package model;

import javafx.geometry.Point2D;
import model.player.Player;

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

    default boolean isFoodEaten(Player player) {
        return getFoodCoordinates().equals(player.getSnake().getHeadCoordinates());
    }

    Point2D getFoodCoordinates();

    void deleteFood();

    void spawnFood();



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
