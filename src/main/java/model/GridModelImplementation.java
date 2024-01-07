package model;

import javafx.geometry.Point2D;
import model.food.Food;
import model.player.Bot;
import model.player.Human;
import model.player.Player;

import java.util.ArrayList;
import java.util.List;

public class GridModelImplementation implements GridModel {

    public static final int WIDTH = 32;
    public static final int HEIGHT = 18;

    private final List<Player> players;
    private final List<Human> humanPlayers;
    private final List<Bot> computerPlayers;

    private final Food food;

    public GridModelImplementation(List<Human> humanPlayers, List<Bot> computerPlayers) {
        this.food = new Food(this);
        spawnFood();
        this.humanPlayers = humanPlayers;
        this.computerPlayers = computerPlayers;

        this.players = new ArrayList<>();
        this.players.addAll(humanPlayers);
        this.players.addAll(computerPlayers);
    }

    @Override
    public List<Human> getHumanPlayers() {
        return humanPlayers;
    }

    @Override
    public List<Bot> getComputerPlayers() {
        return computerPlayers;
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
