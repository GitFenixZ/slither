package model.food;

import javafx.geometry.Point2D;

public class Food {

    Point2D coordinates;

    public Food(Point2D coordinates) {
        this.coordinates = coordinates;
    }

    public Point2D getCoordinates() {
        return new Point2D(coordinates.getX(), coordinates.getY());
    }

}
