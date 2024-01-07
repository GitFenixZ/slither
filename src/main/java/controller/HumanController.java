package controller;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyEvent;
import model.Direction;
import model.GridModel;
import model.player.Bot;
import model.player.Human;
import model.player.Player;
import view.GridView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class HumanController {


    /**
     * Initializes the keyboard controller by adding event handlers for key presses.
     *
     * @param model      The grid model
     * @param view       The grid view.
     * @param controller The grid controller.
     */
    public static void initKeyboardController(GridModel model, GridView view, GridController controller) {
        view.getScene().addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {
            boolean humansMoved = false;
            switch (event.getCode()) {
                case UP:
                    humansMoved = moveHumans(model, controller, Direction.UP);
                    break;
                case DOWN:
                    humansMoved = moveHumans(model, controller, Direction.DOWN);
                    break;
                case LEFT:
                    humansMoved = moveHumans(model, controller, Direction.LEFT);
                    break;
                case RIGHT:
                    humansMoved = moveHumans(model, controller, Direction.RIGHT);
                    break;
                default:
                    break;
            }
            if (humansMoved) {
                moveComputers(model, controller);
            }
        });

    }

    /**
     * Move every human-controlled player according to the specified direction
     *
     * @param model      The grid model
     * @param controller The grid controller
     * @param direction  The direction to take
     * @return true if the direction is valid for the players, false otherwise
     */
    private static boolean moveHumans(GridModel model, GridController controller, Direction direction) {
        for (Human player : model.getHumanPlayers()) {
            if (direction != player.getSnake().getHead().getDirection().getOppositeDirection()) {
                controller.movePlayer(player, direction);
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * Move every computer-controlled snakes according to the computer strategy
     *
     * @param model      The grid model
     * @param controller The grid controller
     */
    private static void moveComputers(GridModel model, GridController controller) {
        for (Bot cpu : model.getComputerPlayers()) {
            Direction direction = chooseDirection(model, cpu);
            if (direction != null) {
                controller.movePlayer(cpu, direction);
            }
        }
    }

    /**
     * Choose a random direction, trying to avoid other snakes
     *
     * @param model  The grid model
     * @param player The player to move
     * @return a direction avoiding another snake if possible
     */
    private static Direction chooseDirection(GridModel model, Bot player) {
        List<Direction> possibleDirections = getPossibleDirections(model, player);
        List<Direction> survivingDirections = getSurvivingDirections(model, player, possibleDirections);

        Direction randomDirection = getRandomDirection(possibleDirections);
        if (!survivingDirections.isEmpty()) {
            randomDirection = getRandomDirection(survivingDirections);
        }

        return randomDirection;
    }

    /**
     * List all the valid directions for the computer-controlled snake
     *
     * @param model  The grid model
     * @param player The player to move
     * @return list containing all valid directions
     */
    private static List<Direction> getPossibleDirections(GridModel model, Bot player) {
        List<Direction> possibleDirections =
                new ArrayList<>(Arrays.asList(Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT));
        possibleDirections.removeIf((Direction d) -> {
            Point2D snakeCoordinates = player.getSnake().getHead().getCoordinates();
            Point2D futurePosition = snakeCoordinates.add(d.getVectorOfDirection());
            return d == player.getSnake().getHead().getDirection().getOppositeDirection() || !model.isInsideGrid(futurePosition);
        });

        return possibleDirections;
    }

    /**
     * Lists the safe-from-other-snakes directions from a list of directions
     *
     * @param model              The grid model
     * @param player             The player to move
     * @param possibleDirections The list of directions to filter
     * @return a list containing the safe directions to choose
     */
    private static List<Direction> getSurvivingDirections(GridModel model,
                                                          Bot player,
                                                          List<Direction> possibleDirections) {
        List<Direction> survivingDirections = new ArrayList<>(possibleDirections);
        List<Point2D> dangerCells = getAllDangerZones(model, player);

        survivingDirections.removeIf((Direction d) -> {
            Point2D arrivalCell = player.getSnake().getHead().getCoordinates().add(d.getVectorOfDirection());
            return dangerCells.contains(arrivalCell);
        });

        return survivingDirections;
    }

    /**
     * Gets all cells presenting danger for the specified player
     *
     * @param model  The grid model
     * @param player The player to move
     * @return The list containing all dangerous cells for the specified player
     */
    private static List<Point2D> getAllDangerZones(GridModel model, ComputerPlayerImplementation player) {
        List<Point2D> dangerCells = new ArrayList<>();
        List<Player> players = model.getPlayers();
        for (Player pl : players) {
            if (!pl.equals(player)) {
                dangerCells.addAll(pl.getSnake().getDangerZone());
            }
        }
        return dangerCells;
    }

    /**
     * Chooses a random direction from a list
     *
     * @param directions The list of directions to choose from
     * @return a random valid direction
     */
    private static Direction getRandomDirection(List<Direction> directions) {
        int nbOfPossibleDirections = directions.size();
        if (nbOfPossibleDirections != 0) {
            Random rand = new Random();
            return directions.get(rand.nextInt(nbOfPossibleDirections));
        }
        return null;
    }

}
