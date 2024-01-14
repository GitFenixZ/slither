package controller;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyEvent;
import model.Direction;
import model.GridModel;
import view.GridView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BotController {

    /**
     * Initializes the computer controller by adding event handlers for key presses.
     *
     * @param model      The grid model.
     * @param view       The grid view.
     * @param controller The grid controller.
     */
    public static void initComputerController(GridModel model, GridView view, GridController controller) {
        view.getScene().addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {
            switch (event.getCode()) {
                case UP:
                case DOWN:
                case LEFT:
                case RIGHT:
                    Direction randomDirection = getRandomDirection(model);
                    if (randomDirection != null) {
                        controller.movePlayer(model.getComputerPlayer(), randomDirection);
                    }
                    break;
                default:
                    break;
            }
        });
    }

    /**
     * Chooses a random valid direction for the computer-controlled snake
     *
     * @param model The grid model
     * @return a random valid direction
     */
    private static Direction getRandomDirection(GridModel model) {
        List<Direction> possibleDirections = getPossibleDirections(model);
        int nbOfPossibleDirections = possibleDirections.size();
        if (nbOfPossibleDirections != 0) {
            Random rand = new Random();
            return possibleDirections.get(rand.nextInt(nbOfPossibleDirections));
        }
        return null;
    }

    /**
     * List all the valid directions for the computer-controlled snake
     *
     * @param model The grid model
     * @return list containing all valid directions
     */
    private static List<Direction> getPossibleDirections(GridModel model) {
        List<Direction> possibleDirections =
                new ArrayList<>(Arrays.asList(Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT));
        possibleDirections.removeIf((Direction d) -> {
            Point2D snakeCoordinates = model.getComputerPlayer().getSnake().getHead().getCoordinates();
            Point2D futurePosition = snakeCoordinates.add(d.getVectorOfDirection());
            return !model.isInsideGrid(futurePosition);
        });

        return possibleDirections;
    }

}
