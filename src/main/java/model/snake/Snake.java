package model.snake;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import model.Direction;
import model.segment.BasicSegment;
import model.segment.Segment;

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

    public Segment getTail() {
        return getSegmentAtIndex(getLength() - 1);
    }

    public Point2D getHeadCoordinates() {
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

    /**
     * Checks if the head of the snake entered in collision with another snake
     *
     * @param other the opponent snake
     * @return true if the head of the snake is colliding with the other
     */
    public boolean collidedWith(Snake other) {
        boolean otherIsThis = other.getHead().equals(getHead());
        for (Segment s : other.segments) {
            if ((!otherIsThis || !s.equals(getHead())) && getHeadCoordinates().equals(s.getCoordinates())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get the list of positions where the snake's head could be on the next move
     *
     * @return the list of positions where the snake's head could be on the next move
     */
    public List<Point2D> getPotentialHeadPositions() {
        List<Point2D> dangerZone = new ArrayList<>();

        for (int col = -1; col <= 1; col++) {
            for (int row = -1; row <= 1; row++) {
                if (isMoveVectorAllowed(row, col)) {
                    dangerZone.add(new Point2D(getHeadX() + col, getHeadY() + row));
                }
            }
        }

        return dangerZone;
    }

    /**
     * Checks if a move from row horizontally and col vertically is allowed
     *
     * @param row The horizontal value
     * @param col The vertical Value
     * @return true if a move from row horizontally and col vertically is allowed, false otherwise
     */
    private boolean isMoveVectorAllowed(int row, int col) {
        Point2D resultOppositeCurrentDirection = getHead().getDirection().getOppositeDirection().getVectorOfDirection();
        return !resultOppositeCurrentDirection.equals(new Point2D(col, row)) &&
                ((Math.abs(row) == 1 && col == 0) || (row == 0 && Math.abs(col) == 1));
    }

    /**
     * Get the list of positions where an opponent is sure to collide with this snake on the next move
     *
     * @return the list of positions where an opponent is sure to collide with this snake on the next move
     */
    public List<Point2D> getGuaranteedHitBox() {
        List<Point2D> deathZone = new ArrayList<>();

        //Second, adding the segments of the snake, minus the tail
        for (int i = 0; i < getSegments().size() - 1; i++) {
            Point2D cell = getSegmentAtIndex(i).getCoordinates();
            if (!deathZone.contains(cell)) {
                deathZone.add(cell);
            }
        }

        return deathZone;
    }

    public void grow() {
        Segment tail = getTail();
        Direction tailDirection = tail.getDirection();

        Point2D newTailCoordinates = tail.getCoordinates().add(
                tailDirection.getOppositeDirection().getVectorOfDirection()
        );

        Segment newTail = new BasicSegment(
                newTailCoordinates,
                tailDirection
        );

        segments.add(newTail);
    }

    public static class Builder {
        private static final int DEFAULT_SNAKE_LENGTH = 3;
        private List<Segment> segments = null;
        private Point2D coordinates;
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
            if (coordinates == null) {
                coordinates = SnakePlacer.getNextStartingPosition();
            }
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
                tmp = tmp.add(Direction.LEFT.getVectorOfDirection());
            }

            return res;
        }
    }
}
