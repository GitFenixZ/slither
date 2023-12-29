package model;

import javafx.geometry.Point2D;

public enum Direction {

    UP,
    DOWN,
    LEFT,
    RIGHT;

    public static final int STEP = 1; // The step increment for each move.

    /**
     * Returns the coordinates matching the current direction.
     *
     * @return coordinates matching the current direction
     */
    public Point2D getCoordinates() {
        return switch (this) {
            case UP -> new Point2D(0, -STEP);
            case DOWN -> new Point2D(0, STEP);
            case LEFT -> new Point2D(-STEP, 0);
            case RIGHT -> new Point2D(STEP, 0);
        };
    }

    /**
     * Returns the opposite direction of the current one.
     *
     * @return opposite direction of the current one
     */
    public Direction getOpposite() {
        return switch (this) {
            case UP -> DOWN;
            case DOWN -> UP;
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
        };
    }

}
