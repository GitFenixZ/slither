package controller;

import javafx.scene.input.KeyEvent;
import model.Direction;
import model.player.HumanPlayerImplementation;
import view.GridView;

public class HumanController {


    /**
     * Initializes the keyboard controller by adding event handlers for key presses.
     *
     * @param player     The player to control.
     * @param view       The grid view.
     * @param controller The grid controller.
     */
    public static void initKeyboardController(HumanPlayerImplementation player,
                                            GridView view,
                                            GridController controller) {
        view.getScene().addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {
            switch (event.getCode()) {
                case UP:
                    controller.movePlayer(player, Direction.UP);
                    break;
                case DOWN:
                    controller.movePlayer(player, Direction.DOWN);
                    break;
                case LEFT:
                    controller.movePlayer(player, Direction.LEFT);
                    break;
                case RIGHT:
                    controller.movePlayer(player, Direction.RIGHT);
                    break;
                default:
                    break;
            }
        });

    }

}
