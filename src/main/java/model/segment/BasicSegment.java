package model.segment;

import javafx.geometry.Point2D;
import model.Direction;

import java.util.Objects;

public class BasicSegment implements Segment {
    private Point2D coordinates;
    private Direction direction;

    public BasicSegment(Point2D coordinates, Direction direction) {
        this.coordinates = new Point2D(coordinates.getX(), coordinates.getY());
        this.direction = direction;
    }

    @Override
    public Point2D getCoordinates() {
        return new Point2D(coordinates.getX(), coordinates.getY());
    }

    @Override
    public int getX() {
        // Casting to int is okay because our STEP is 1.
        return (int) coordinates.getX();
    }

    @Override
    public int getY() {
        // Casting to int is okay because our STEP is 1.
        return (int) coordinates.getY();
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public void moveToDirection(Direction direction) {
        this.direction = direction;
        coordinates = coordinates.add(direction.getVectorOfDirection());
    }

    @Override
    public Segment copy() {
        return new BasicSegment(coordinates, direction);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasicSegment that = (BasicSegment) o;
        return Objects.equals(coordinates, that.coordinates) && direction == that.direction;
    }

}
