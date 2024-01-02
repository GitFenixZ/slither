package controller;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import model.Direction;
import model.GridModel;
import view.GridView;

public class KeyboardController {
    public static void initKeyboardController(GridModel model, GridView view, GridController controller) {
        view.getScene().addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {
            switch (event.getCode()) {
                case UP:
                    controller.movePlayer(model.getPlayer(), Direction.UP);
                    break;
                case DOWN:
                    controller.movePlayer(model.getPlayer(), Direction.DOWN);
                    break;
                case LEFT:
                    controller.movePlayer(model.getPlayer(), Direction.LEFT);
                    break;
                case RIGHT:
                    controller.movePlayer(model.getPlayer(), Direction.RIGHT);
                    break;
                default:
                    break;
            }
        });

    }

}
