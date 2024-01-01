package model.player;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import model.Snake;

public abstract class PlayerBuilder<T extends Player> {
    protected Color color;
    protected String name;
    protected Snake snake;

    public PlayerBuilder<T> color(Color color) {
        this.color = color;
        return this;
    }

    public PlayerBuilder<T> name(String name) {
        this.name = name;
        return this;
    }

    public PlayerBuilder<T> snake(Snake snake) {
        this.snake = snake.copy();
        return this;
    }

    public abstract T build();
}
