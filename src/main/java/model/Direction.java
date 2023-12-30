package model;

import javafx.geometry.Point2D;

public enum Direction {

    UP,
    DOWN,
    LEFT,
    RIGHT;

    public static final int STEP = 1; // The step increment for each move.

    /**
     * Returns the vector matching the current direction.
     *
     * @return the vector matching the current direction.
     */
    public Point2D getVectorOfDirection() {
        return switch (this) {
            case UP -> new Point2D(0, -STEP);
            case DOWN -> new Point2D(0, STEP);
            case LEFT -> new Point2D(-STEP, 0);
            case RIGHT -> new Point2D(STEP, 0);
        };
    }

}
