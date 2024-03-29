package model;

import javafx.geometry.Point2D;
import model.segment.BasicSegment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBasicSegment {

    /**
     * Move one step to the right / Expected coordinates : (1, 0)
     */
    @Test
    void moveToDirection_OneRight() {
        BasicSegment seg = new BasicSegment(Point2D.ZERO, Direction.RIGHT);
        seg.moveToDirection(Direction.RIGHT);
        assertEquals(1, seg.getX());
        assertEquals(0, seg.getY());
    }

    /**
     * Move one step to the left / Expected coordinates : (-1, 0)
     */
    @Test
    void moveToDirection_OneLeft() {
        BasicSegment seg = new BasicSegment(Point2D.ZERO, Direction.LEFT);
        seg.moveToDirection(Direction.LEFT);
        assertEquals(-1, seg.getX());
        assertEquals(0, seg.getY());
    }

    /**
     * Move one step up / Expected coordinates : (0, -1)
     */
    @Test
    void moveToDirection_OneUp() {
        BasicSegment seg = new BasicSegment(Point2D.ZERO, Direction.UP);
        seg.moveToDirection(Direction.UP);
        assertEquals(0, seg.getX());
        assertEquals(-1, seg.getY());
    }

    /**
     * Move one step down / Expected coordinates : (0, 1)
     */
    @Test
    void moveToDirection_OneDown() {
        BasicSegment seg = new BasicSegment(Point2D.ZERO, Direction.DOWN);
        seg.moveToDirection(Direction.DOWN);
        assertEquals(0, seg.getX());
        assertEquals(1, seg.getY());
    }

    /**
     * Move following a certain list of moves / Expected coordinates : (2, -2)
     */
    @Test
    void moveToDirection_Combination() {
        BasicSegment seg = new BasicSegment(Point2D.ZERO, Direction.DOWN);
        seg.moveToDirection(Direction.UP);
        seg.moveToDirection(Direction.DOWN);
        seg.moveToDirection(Direction.UP);
        seg.moveToDirection(Direction.DOWN);
        seg.moveToDirection(Direction.LEFT);
        seg.moveToDirection(Direction.LEFT);
        seg.moveToDirection(Direction.RIGHT);
        seg.moveToDirection(Direction.RIGHT);
        seg.moveToDirection(Direction.RIGHT);
        seg.moveToDirection(Direction.RIGHT);
        seg.moveToDirection(Direction.UP);
        seg.moveToDirection(Direction.UP);
        assertEquals(2, seg.getX());
        assertEquals(-2, seg.getY());
    }
}
