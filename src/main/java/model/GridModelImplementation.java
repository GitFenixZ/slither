package model;

import javafx.geometry.Point2D;
import model.player.Player;
import model.food.Food;

import java.util.ArrayList;
import java.util.List;

public class GridModelImplementation implements GridModel {

    public static final int WIDTH = 32;
    public static final int HEIGHT = 18;

    private final Player human;
    private final Player computer;
    private final Food food;

    public GridModelImplementation(Player human, Player computer) {
        this.human = human;
        this.computer = computer;
        this.food = new Food(this);
        spawnFood();
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

    public Point2D getFoodCoordinates() {
        return food.getCoordinates();
    }

    @Override
    public Food getFood() {
        return food;
    }
}
