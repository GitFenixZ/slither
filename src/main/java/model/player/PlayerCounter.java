package model.player;

public class PlayerCounter {
    private static int count = 0;

    /**
     * Returns the next id to be assigned to a player and increments the count
     *
     * @return the next id to be assigned
     */
    public static int assignId() {
        int tmp = count;
        count++;
        return tmp;
    }
}
