package model;

public interface Segment {
    int getCurrentX();
    int getCurrentY();

    default boolean isMoveValid(int x, int y) {
        return (Math.abs(x - getCurrentX()) == 1 && y - getCurrentY() == 0)
                || (Math.abs(y -getCurrentY()) == 1 && x - getCurrentX() == 0);
    }

    void moveToPosition(int x, int y);
}
