package model;

import javafx.geometry.Point2D;

public interface Segment {

    Point2D getCoordinates();

    int getX();

    int getY();

    Direction getDirection();

    void moveToDirection(Direction direction);

    Segment copy();

}
