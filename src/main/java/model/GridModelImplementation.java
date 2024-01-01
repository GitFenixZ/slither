package model;

import model.player.Player;

public class GridModelImplementation implements GridModel {

    private static final int WIDTH = 32;
    private static final int HEIGHT = 18;
    private final Player player;

    public GridModelImplementation(Player player) {
        this.player = player;
    }

    @Override
    public Player getPlayer() {
        return player;
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
