package model;

import javafx.geometry.Point2D;

public enum Direction {

    UP,
    DOWN,
    LEFT,
    RIGHT;

    public static final int STEP = 1;

    public Point2D getCoordinates() {
        return switch (this) {
            case UP -> new Point2D(0, -STEP);
            case DOWN -> new Point2D(0, STEP);
            case LEFT -> new Point2D(-STEP, 0);
            case RIGHT -> new Point2D(STEP, 0);
            default -> throw new IllegalArgumentException("Invalid direction");
        };
    }

    public Direction getOpposite() {
        return switch (this) {
            case UP -> DOWN;
            case DOWN -> UP;
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
            default -> throw new IllegalArgumentException("Invalid direction");
        };
    }

}
