package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBasicSegment {

    /**
     * Move one step to the right / Expected coordinates : (1, 0)
     */
    @Test
    void testMoveToPositionOneRight() {
        BasicSegment seg = new BasicSegment(0, 0);
        seg.moveToPosition(1, 0);
        assertEquals(1, seg.getCurrentX());
        assertEquals(0, seg.getCurrentY());
    }

    /**
     * Move one step to the left / Expected coordinates : (-1, 0)
     */
    @Test
    void testMoveToPositionOneLeft() {
        BasicSegment seg = new BasicSegment(0, 0);
        seg.moveToPosition(-1, 0);
        assertEquals(-1, seg.getCurrentX());
        assertEquals(0, seg.getCurrentY());
    }

    /**
     * Move one step up / Expected coordinates : (0, -1)
     */
    @Test
    void testMoveToPositionOneUp() {
        BasicSegment seg = new BasicSegment(0, 0);
        seg.moveToPosition(0, -1);
        assertEquals(0, seg.getCurrentX());
        assertEquals(-1, seg.getCurrentY());
    }

    /**
     * Move one step down / Expected coordinates : (0, 1)
     */
    @Test
    void testMoveToPositionOneDown() {
        BasicSegment seg = new BasicSegment(0, 0);
        seg.moveToPosition(0, 1);
        assertEquals(0, seg.getCurrentX());
        assertEquals(1, seg.getCurrentY());
    }

    /**
     * Move following a certain list of moves / Expected coordinates : (2, -2)
     */
    @Test
    void testMoveToPositionCombination1() {
        BasicSegment seg = new BasicSegment(0, 0);
        seg.moveToPosition(1, 0);
        seg.moveToPosition(2, 0);
        seg.moveToPosition(2, -1);
        seg.moveToPosition(2, -2);
        assertEquals(2, seg.getCurrentX());
        assertEquals(-2, seg.getCurrentY());
    }

    /**
     * Move following a certain list of moves / Expected coordinates : (0, 0)
     */
    @Test
    void testMoveToPositionCombination2() {
        BasicSegment seg = new BasicSegment(0, 0);
        seg.moveToPosition(1, 0);
        seg.moveToPosition(2, 0);
        seg.moveToPosition(3, 0);
        seg.moveToPosition(3, 1);
        seg.moveToPosition(2, 1);
        seg.moveToPosition(2, 0);
        seg.moveToPosition(2, -1);
        seg.moveToPosition(1, -1);
        seg.moveToPosition(1, 0);
        seg.moveToPosition(0, 0);
        assertEquals(0, seg.getCurrentX());
        assertEquals(0, seg.getCurrentY());
    }
}
