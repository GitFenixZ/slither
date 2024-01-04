package model.player;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import model.Direction;
import model.GridModelImplementation;
import model.Snake;

public abstract class PlayerImplementation implements Player {

    private final Color color;
    private final String name;
    private final Snake snake;

    PlayerImplementation(PlayerBuilder<? extends PlayerImplementation> builder) {
        this.color = builder.color;
        this.name = builder.name;
        this.snake = builder.snake;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Snake getSnake() {
        return snake;
    }

    @Override
    public void moveToDirection(Direction direction) {
        snake.moveToDirection(direction);
    }

}
