package model;

import javafx.geometry.Point2D;

class BasicSegment implements Segment {
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
}
