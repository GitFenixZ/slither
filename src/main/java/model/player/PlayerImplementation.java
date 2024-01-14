package model.player;

import javafx.scene.paint.Color;
import model.Direction;
import model.Snake;

public abstract class PlayerImplementation implements Player {
    private final int id;
    private final Color color;
    private final String name;
    private final Snake snake;

    PlayerImplementation(PlayerBuilder<? extends PlayerImplementation> builder) {
        this.id = PlayerCounter.assignId();
        this.color = builder.color;
        this.name = builder.name;
        this.snake = builder.snake;
    }

    @Override
    public int getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerImplementation that = (PlayerImplementation) o;
        return id == that.id;
    }
}
