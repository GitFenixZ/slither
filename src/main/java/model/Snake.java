package model;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private final Color color;
    private final List<Segment> segments;

    Snake(Builder builder) {
        color = builder.color;
        segments = new ArrayList<>();

        Point2D tmp = new Point2D(builder.coordinates.getX(), builder.coordinates.getY());
        for (int i = 0; i < builder.length; i++) {
            segments.add(new BasicSegment(tmp, Direction.RIGHT));
            tmp = tmp.add(Direction.RIGHT.getVectorOfDirection());
        }
    }

    public Color getColor() {
        return color;
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

    public static class Builder {
        private int length = 1; // Default length is 1
        private Point2D coordinates = Point2D.ZERO;
        private Color color = Color.BLUE;

        public Builder length(int length) {
            this.length = length;
            return this;
        }

        public Builder coordinates(Point2D coordinates) {
            this.coordinates = new Point2D(coordinates.getX(), coordinates.getY());
            return this;
        }

        public Builder color(Color color) {
            this.color = color;
            return this;
        }

        public Snake build() {
            return new Snake(this);
        }
    }
}
