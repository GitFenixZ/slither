package model;

import model.player.Player;

public class GridModelImplementation implements GridModel {

    public static final int WIDTH = 32;
    public static final int HEIGHT = 18;

    private final Player human;
    private final Player computer;

    public GridModelImplementation(Player human, Player computer) {
        this.human = human;
        this.computer = computer;
    }

    @Override
    public Player getHumanPlayer() {
        return human;
    }

    @Override
    public Player getComputerPlayer() {
        return computer;
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }
}
