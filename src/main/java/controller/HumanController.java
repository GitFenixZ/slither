package controller;

import javafx.scene.input.KeyEvent;
import model.Direction;
import model.GridModel;
import view.GridView;

public class HumanController {


    /**
     * Initializes the keyboard controller by adding event handlers for key presses.
     *
     * @param model      The grid model.
     * @param view       The grid view.
     * @param controller The grid controller.
     */
    public static void initKeyboardController(GridModel model, GridView view, GridController controller) {
        view.getScene().addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {
            switch (event.getCode()) {
                case UP:
                    controller.movePlayer(model.getHumanPlayer(), Direction.UP);
                    break;
                case DOWN:
                    controller.movePlayer(model.getHumanPlayer(), Direction.DOWN);
                    break;
                case LEFT:
                    controller.movePlayer(model.getHumanPlayer(), Direction.LEFT);
                    break;
                case RIGHT:
                    controller.movePlayer(model.getHumanPlayer(), Direction.RIGHT);
                    break;
                default:
                    break;
            }
        });

    }

}
