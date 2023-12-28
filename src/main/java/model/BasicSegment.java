package model;

public class BasicSegment implements Segment {
    private int currentX;
    private int currentY;

    public BasicSegment(int x, int y) {
        currentX = x;
        currentY = y;
    }

    @Override
    public int getCurrentX() {
        return currentX;
    }

    @Override
    public int getCurrentY() {
        return currentY;
    }

    @Override
    public BasicSegment copy() {
        return new BasicSegment(currentX, currentY);
    }

    @Override
    public void moveToPosition(int x, int y) {
        if (isMoveValid(x, y)) {
            currentX = x;
            currentY = y;
        } else {
            throw new IllegalArgumentException("Invalid move");
        }
    }
}
