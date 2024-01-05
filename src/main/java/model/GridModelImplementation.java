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
    private Food food = null;

    public GridModelImplementation(Player human, Player computer) {
        this.human = human;
        this.computer = computer;
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

    @Override
    public void deleteFood() {
        if (food == null) {
            return;
        }

        food = null;
    }

    @Override
    public void spawnFood() {
        if (food != null) {
            return;
        }

        List<Point2D> freeCoordinates = getFreeCoordinates();
        if (freeCoordinates.isEmpty()) {
            return;
        }

        int randomIndex = (int) (Math.random() * freeCoordinates.size());
        food = new Food(freeCoordinates.get(randomIndex));
    }

    private List<Point2D> getFreeCoordinates() {
        List<Point2D> freeCoordinates = new ArrayList<>();

        for (int row = 0; row < getHeight(); row++) {
            for (int column = 0; column < getWidth(); column++) {
                Point2D coordinates = new Point2D(column, row);
                freeCoordinates.add(coordinates);
            }
        }

        return freeCoordinates.stream().filter(
                this::isFreeCoordinates
        ).toList();
    }

    private boolean isFreeCoordinates(Point2D coordinates) {
        return !human.isOnCoordinates(coordinates) &&
                !computer.isOnCoordinates(coordinates);
    }

    public Point2D getFoodCoordinates() {
        return food.getCoordinates();
    }
}
