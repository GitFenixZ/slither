package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestSegment {
    private final Segment seg00 = new BasicSegment(0, 0);

    /**
     * Check the feasibility of moving one step to the right / Expected : true
     */
    @Test
    void testIsMoveValidOneRight() {
        assertTrue(seg00.isMoveValid(1, 0));
    }

    /**
     * Check the feasibility of moving one step to the left / Expected : true
     */
    @Test
    void testIsMoveValidOneLeft() {
        assertTrue(seg00.isMoveValid(-1, 0));
    }

    /**
     * Check the feasibility of moving one step up / Expected : true
     */
    @Test
    void testIsMoveValidOneUp() {
        assertTrue(seg00.isMoveValid(0, -1));
    }

    /**
     * Check the feasibility of moving one step down / Expected : true
     */
    @Test
    void testIsMoveValidOneDown() {
        assertTrue(seg00.isMoveValid(0, 1));
    }

    /**
     * Check the feasibility of moving two steps to the right / Expected : false
     */
    @Test
    void testIsMoveValidTwoRight() {
        assertFalse(seg00.isMoveValid(2, 0));
    }

    /**
     * Check the feasibility of moving two steps to the left / Expected : false
     */
    @Test
    void testIsMoveValidTwoLeft() {
        assertFalse(seg00.isMoveValid(-2, 0));
    }

    /**
     * Check the feasibility of moving two steps up / Expected : false
     */
    @Test
    void testIsMoveValidTwoUp() {
        assertFalse(seg00.isMoveValid(0, -2));
    }

    /**
     * Check the feasibility of moving two steps down / Expected : false
     */
    @Test
    void testIsMoveValidTwoDown() {
        assertFalse(seg00.isMoveValid(0, 2));
    }

    /**
     * Check the feasibility of moving in diagonal / Expected : false
     */
    @Test
    void testIsMoveValidDiagonalUpRight() {
        assertFalse(seg00.isMoveValid(1, -1));
    }

    /**
     * Check the feasibility of moving in diagonal / Expected : false
     */
    @Test
    void testIsMoveValidDiagonalUpLeft() {
        assertFalse(seg00.isMoveValid(-1, -1));
    }

    /**
     * Check the feasibility of moving in diagonal / Expected : false
     */
    @Test
    void testIsMoveValidDiagonalDownRight() {
        assertFalse(seg00.isMoveValid(1, 1));
    }

    /**
     * Check the feasibility of moving in diagonal / Expected : false
     */
    @Test
    void testIsMoveValidDiagonalDownLeft() {
        assertFalse(seg00.isMoveValid(-1, 1));
    }
}
