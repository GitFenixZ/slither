package model;

public interface Segment {
    static final int DEFAULT_MOVE_STEP = 1;

    int getCurrentX();

    int getCurrentY();

    /** Without taking the grid into account, check if the target position can be attained
     * @param x the horizontal coordinate of the target position
     * @param y the vertical coordinate of the target position
     * @return true if the target position can be attained from the current one, false otherwise
     */
    default boolean isMoveValid(int x, int y) {
        return (Math.abs(x - getCurrentX()) == DEFAULT_MOVE_STEP && y - getCurrentY() == 0)
                || (Math.abs(y - getCurrentY()) == DEFAULT_MOVE_STEP && x - getCurrentX() == 0);
    }

    /**
     * Move a segment to a given position
     *
     * @param x the horizontal coordinate of the new position
     * @param y the vertical coordinate of the new position
     */
    void moveToPosition(int x, int y);
}
