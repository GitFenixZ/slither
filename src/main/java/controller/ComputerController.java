package controller;

import javafx.scene.input.KeyEvent;
import model.Direction;
import model.GridModel;
import view.GridView;

import java.util.Random;

public class ComputerController {

    public static void initComputerController(GridModel model, GridView view, GridController controller) {
        Random rand = new Random();

        view.getScene().addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {
            switch (event.getCode()) {
                case UP:
                case DOWN:
                case LEFT:
                case RIGHT:
                    int randNum = rand.nextInt(3);
                    Direction randDir = switch (randNum) {
                        case 0 -> Direction.UP;
                        case 1 -> Direction.DOWN;
                        case 2 -> Direction.LEFT;
                        default -> Direction.RIGHT;
                    };
                    controller.movePlayer(model.getComputer(), randDir);
                    break;
                default:
                    break;
            }
        });
    }
}
