package model;

import javafx.geometry.Point2D;

/**
 * Interafce representing {@link Snake}'s segment in a slither game.
 */
public interface Segment {

    /**
     * Gets the copy of the coordinates of the segment.
     *
     * @return the copy of the coordinates of the segment
     */
    Point2D getCoordinates();

    /**
     * Gets the x-coordinate of the segment.
     *
     * @return the x-coordinate of the segment
     */
    int getX();

    /**
     * Gets the y-coordinate of the segment.
     *
     * @return the y-coordinate of the segment
     */
    int getY();

    /**
     * Gets the direction of the segment.
     *
     * @return the direction of the segment
     */
    Direction getDirection();

    /**
     * Moves the segment to the specified direction.
     *
     * @param direction the direction to move the segment
     */
    void moveToDirection(Direction direction);

    /**
     * Creates a deep copy of the segment.
     *
     * @return a deep copy of the segment
     */
    Segment copy();

}
