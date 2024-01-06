package controller;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyEvent;
import model.Direction;
import model.GridModel;
import model.player.ComputerPlayerImplementation;
import model.player.Player;
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
    public static void initComputerController(GridModel model, ComputerPlayerImplementation player, GridView view, GridController controller) {
        view.getScene().addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {
            if (controller.getPhase() == GridController.Phase.PLAYING) {
                switch (event.getCode()) {
                    case UP:
                    case DOWN:
                    case LEFT:
                    case RIGHT:
                        Direction direction = chooseDirection(model, player);
                        if (direction != null) {
                            controller.movePlayer(player, direction);
                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private static Direction chooseDirection(GridModel model, ComputerPlayerImplementation player) {
        List<Direction> possibleDirections = getPossibleDirections(model, player);
        List<Direction> survivingDirections = getSurvivingDirections(model, player, possibleDirections);

        Direction randomDirection = getRandomDirection(model, player, possibleDirections);
        if (!survivingDirections.isEmpty()) {
            randomDirection = getRandomDirection(model, player, survivingDirections);
        }

        return randomDirection;
    }


    /**
     * Chooses a random valid direction for the computer-controlled snake
     *
     * @param model The grid model
     * @return a random valid direction
     */
    private static Direction getRandomDirection(GridModel model, ComputerPlayerImplementation player, List<Direction> directions) {
        int nbOfPossibleDirections = directions.size();
        if (nbOfPossibleDirections != 0) {
            Random rand = new Random();
            return directions.get(rand.nextInt(nbOfPossibleDirections));
        }
        return null;
    }

    private static List<Direction> getSurvivingDirections(GridModel model, ComputerPlayerImplementation player, List<Direction> possibleDirections) {
        List<Direction> survivingDirections = new ArrayList<>(possibleDirections);
        List<Point2D> dangerCells = getAllDangerZones(model, player);

        survivingDirections.removeIf((Direction d) -> {
            Point2D arrivalCell = player.getSnake().getHead().getCoordinates().add(d.getVectorOfDirection());
            return dangerCells.contains(arrivalCell);
        });

        return survivingDirections;
    }

    /**
     * List all the valid directions for the computer-controlled snake
     *
     * @param model The grid model
     * @return list containing all valid directions
     */
    private static List<Direction> getPossibleDirections(GridModel model, ComputerPlayerImplementation player) {
        List<Direction> possibleDirections =
                new ArrayList<>(Arrays.asList(Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT));
        possibleDirections.removeIf((Direction d) -> {
            Point2D snakeCoordinates = player.getSnake().getHead().getCoordinates();
            Point2D futurePosition = snakeCoordinates.add(d.getVectorOfDirection());
            return !model.isInsideGrid(futurePosition);
        });

        return possibleDirections;
    }

    private static List<Point2D> getAllDangerZones(GridModel model, ComputerPlayerImplementation player) {
        List<Point2D> dangerCells = new ArrayList<>();
        List<Player> players = model.getPlayers();
        for (Player pl : players) {
            if (!pl.equals(player)) {
                dangerCells.addAll(pl.getDangerZone());
            }
        }
        return dangerCells;
    }

}
