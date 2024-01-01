package model;

import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private static final int DEFAULT_SNAKE_SIZE = 1;
    private final List<Segment> segments;

    Snake(Point2D point) {
        segments = new ArrayList<>();

        Point2D tmp = new Point2D(point.getX(), point.getY());
        for (int i = 0; i < DEFAULT_SNAKE_SIZE; i++) {
            segments.add(new BasicSegment(tmp, Direction.RIGHT));
            tmp = tmp.add(Direction.RIGHT.getVectorOfDirection());
        }
    }

    Segment getSegmentAtIndex(int i) {
        return segments.get(i).copy();
    }

    Segment getHead() {
        return getSegmentAtIndex(0);
    }

    Point2D getHeadCoordinates() {
        return getHead().getCoordinates();
    }

    int getHeadX() {
        return getHead().getX();
    }

    int getHeadY() {
        return getHead().getY();
    }

    int getLength() {
        return segments.size();
    }

    /**
     * Moves the snake in the specified direction.
     *
     * @param direction the direction in which the snake should move
     */
    public void moveToDirection(Direction direction) {
        if (!isValidDirection(direction)) {
            return;
        }

        Direction tmp;
        Direction last = direction;
        for (Segment s : segments) {
            tmp = s.getDirection();
            s.moveToDirection(last);
            last = tmp;
        }
    }

    /**
     * Checks if the given direction is valid for the snake's movement.
     * A snake can't move in a direction opposite to its direction.
     *
     * @param direction the direction to be checked
     * @return true if the direction is valid, false otherwise
     */
    private boolean isValidDirection(Direction direction) {
        if (segments.size() == 1) {
            return true;
        }

        return switch (direction) {
            case UP -> getHead().getDirection() != Direction.DOWN;
            case DOWN -> getHead().getDirection() != Direction.UP;
            case LEFT -> getHead().getDirection() != Direction.RIGHT;
            case RIGHT -> getHead().getDirection() != Direction.LEFT;
        };
    }
}
