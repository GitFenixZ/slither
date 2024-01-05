package model;

import javafx.geometry.Point2D;
import model.food.Food;
import model.player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GridModelImplementation implements GridModel {

    public static final int WIDTH = 32;
    public static final int HEIGHT = 18;

    private final List<Player> players;

    private final Food food;

    public GridModelImplementation(Player human, Player computer) {
        this.food = new Food(this);
        spawnFood();

        players = new ArrayList<>(Arrays.asList(human, computer));
    }

    @Override
    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    public Point2D getFoodCoordinates() {
        return food.getCoordinates();
    }

    @Override
    public Food getFood() {
        return food;
    }
}
