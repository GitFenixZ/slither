package model;

import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private static final int DEFAULT_SNAKE_SIZE = 1;
    private final List<Segment> segments;

    Snake(Point2D point) {
        segments = new ArrayList<>();

        Point2D tmp = point;
        for (int i = 0; i < DEFAULT_SNAKE_SIZE; i++) {
            segments.add(new BasicSegment(tmp, Direction.RIGHT));
            tmp = tmp.add(Direction.RIGHT.getCoordinates());
        }
    }

    Segment getSegmentAtIndex(int i) {
        return segments.get(i).copy();
    }

    Segment getHead() {
        return getSegmentAtIndex(0);
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
