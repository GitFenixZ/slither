package controller.grid;

import model.Direction;
import model.grid.GridModel;
import model.player.Player;
import view.grid.GridView;

/**
 * The GridController interface represents a controller for the game grid.
 */
public interface GridController {

    /**
     * Gets the model associated with the grid controller.
     *
     * @return the grid model
     */
    GridModel getModel();

    /**
     * Gets the view associated with the grid controller.
     *
     * @return the grid view
     */
    GridView getView();

    /**
     * Moves the specified player in the given direction.
     *
     * @param player    the player to move
     * @param direction the direction to move the player
     */
    void movePlayer(Player player, Direction direction);

}
