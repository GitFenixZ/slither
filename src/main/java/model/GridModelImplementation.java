package model;

import model.player.Player;

public class GridModelImplementation implements GridModel {

    public static final int WIDTH = 32;
    public static final int HEIGHT = 18;
    private final Player player;

    private final Player computer;

    public GridModelImplementation(Player player, Player computer) {
        this.player = player;
        this.computer = computer;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public Player getComputer() {
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
