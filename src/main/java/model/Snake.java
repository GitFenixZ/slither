package model;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private final Color color;
    private final List<Segment> segments;

    Snake(Builder builder) {
        this.segments = builder.segments;
        this.color = builder.color;
    }

    public Color getColor() {
        return color;
    }

    public Segment getSegmentAtIndex(int i) {
        return segments.get(i).copy();
    }

    public Segment getHead() {
        return getSegmentAtIndex(0);
    }

    Point2D getHeadCoordinates() {
        return getHead().getCoordinates();
    }

    public int getHeadX() {
        return getHead().getX();
    }

    public int getHeadY() {
        return getHead().getY();
    }

    public int getLength() {
        return segments.size();
    }

    public List<Segment> getSegments() {
        List<Segment> res = new ArrayList<>();
        segments.forEach(segment -> res.add(segment.copy()));
        return res;
    }

    /**
     * Moves the snake in the specified direction.
     *
     * @param direction the direction in which the snake should move
     */
    public void moveToDirection(Direction direction) {
        Direction tmp;
        Direction last = direction;
        for (Segment s : segments) {
            tmp = s.getDirection();
            s.moveToDirection(last);
            last = tmp;
        }
    }

    public Snake copy() {
        return new Builder()
                .segments(segments)
                .coordinates(getHeadCoordinates())
                .color(getColor())
                .build();
    }

    public static class Builder {
        private static final int DEFAULT_SNAKE_LENGTH = 1;
        // TODO: fix default spawning coordinates to middle of the grid
        private List<Segment> segments = null;
        private Point2D coordinates = Point2D.ZERO;
        private Color color = Color.GREEN;

        public Builder segments(List<Segment> segments) {
            List<Segment> deepCopy = new ArrayList<>();
            segments.forEach(segment -> deepCopy.add(segment.copy()));
            this.segments = deepCopy;

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
            if (segments == null) {
                segments = buildDefaultSegments();
            }

            return new Snake(this);
        }

        private List<Segment> buildDefaultSegments() {
            List<Segment> res = new ArrayList<>();

            Point2D tmp = new Point2D(coordinates.getX(), coordinates.getY());
            for (int i = 0; i < DEFAULT_SNAKE_LENGTH; i++) {
                res.add(new BasicSegment(tmp, Direction.RIGHT));
                // TODO: fix direction (the tails should follow the head)
                tmp = tmp.add(Direction.RIGHT.getVectorOfDirection());
            }

            return res;
        }
    }
}
