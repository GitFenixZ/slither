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

    /**
     * Creates a deep copy of the snake.
     *
     * @return a deep copy of the snake
     * @apiNote This method relies on the {@link Builder} class to create the deep copy.
     */
    public Snake copy() {
        return new Builder()
                .segments(segments)
                .coordinates(getHeadCoordinates())
                .color(getColor())
                .build();
    }

    public static class Builder {
        private static final int DEFAULT_SNAKE_LENGTH = 1;
        private List<Segment> segments = null;
        private Point2D coordinates = new Point2D((int) (GridModelImplementation.WIDTH / 2), (int) (GridModelImplementation.HEIGHT / 2));
        private Color color = Color.GREEN;

        /**
         * Sets the segments of the snake by deep copying the given list of segments.
         *
         * @param segments the segments of the snake
         * @return the {@link Snake} {@link Builder}'s instance
         */
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
