package model;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<Segment> segments;

    private static final int DEFAULT_SNAKE_SIZE = 1;

    Snake(int headStartX, int headStartY) {
        segments = new ArrayList<>();
        int nextPosY = headStartY;
        for (int i = 0; i < DEFAULT_SNAKE_SIZE; i++) {
            segments.add(new BasicSegment(headStartX, nextPosY));
            nextPosY++;
        }

    }

    /**
     * Move the head of the snake to the given position, dragging the rest of the segments with it
     *
     * @param x the horizontal coordinate of the head's target position
     * @param y the vertical coordinate of the head's target position
     */
    public void moveToPosition(int x, int y) {
        int nextDestX = x;
        int nextDestY = y;
        for (Segment s : segments) {
            int tempX = s.getCurrentX();
            int tempY = s.getCurrentY();
            s.moveToPosition(nextDestX, nextDestY);
            nextDestX = tempX;
            nextDestY = tempY;
        }
    }
}
