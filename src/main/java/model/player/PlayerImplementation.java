package model.player;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import model.Snake;

public class PlayerImplementation implements Player {

    private final Point2D coordinates;
    private final Color color;
    private final Snake snake;
    private final String name;

    public PlayerImplementation(Color color, String name) {
        this.color = color;
        this.name = name;
        this.snake = new Snake(color);
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public Snake getSnake() {
        return snake;
    }

    @Override
    public String getName() {
        return name;
    }
}
