package model;

import javafx.geometry.Point2D;

public interface GridModel {

    int getWidth();

    int getHeight();

    default boolean isInsideGrid(Point2D point) {
        return point.getX() >= 0 && point.getX() < getWidth() &&
                point.getY() >= 0 && point.getY() < getHeight();
    }

    default boolean isMoveValid(Snake snake, Direction direction) {
        Point2D destination = snake.getHead().getCoordinates().add(direction.getVectorOfDirection());
        return isInsideGrid(destination);
    }

}
