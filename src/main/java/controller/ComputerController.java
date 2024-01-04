package controller;

import javafx.scene.input.KeyEvent;
import model.Direction;
import model.GridModel;
import view.GridView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComputerController {

    public static void initComputerController(GridModel model, GridView view, GridController controller) {
        view.getScene().addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {
            switch (event.getCode()) {
                case UP:
                case DOWN:
                case LEFT:
                case RIGHT:
                    Direction randomDirection = getRandomDirection(model);
                    if (randomDirection != null) {
                        controller.movePlayer(model.getComputer(), randomDirection);
                    }
                    break;
                default:
                    break;
            }
        });
    }

    private static Direction getRandomDirection(GridModel model) {
        List<Direction> possibleDirections = getPossibleDirections(model);
        int nbOfPossibleDirections = possibleDirections.size();
        if (nbOfPossibleDirections != 0) {
            Random rand = new Random();
            return possibleDirections.get(rand.nextInt(nbOfPossibleDirections));
        }
        return null;
    }

    private static List<Direction> getPossibleDirections(GridModel model) {
        int currentComputerPositionX = model.getComputer().getSnake().getHeadX();
        int currentComputerPositionY = model.getComputer().getSnake().getHeadY();
        List<Direction> possibleDirections = new ArrayList<>();
        if (currentComputerPositionY != 0) {
            possibleDirections.add(Direction.UP);
        }
        if (currentComputerPositionY != model.getHeight() - 1) {
            possibleDirections.add(Direction.DOWN);
        }
        if (currentComputerPositionX != 0) {
            possibleDirections.add(Direction.LEFT);
        }
        if (currentComputerPositionX != model.getWidth() - 1) {
            possibleDirections.add(Direction.RIGHT);
        }
        return possibleDirections;
    }

}
