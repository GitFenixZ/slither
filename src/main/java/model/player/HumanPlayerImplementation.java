package model.player;

import javafx.scene.paint.Color;
import model.Direction;
import model.Snake;

public class HumanPlayerImplementation implements Player {
    private final Color color;
    private final String name;
    private final Snake snake;

    public HumanPlayerImplementation(Builder builder) {
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
        return snake.copy();
    }

    @Override
    public void moveToDirection(Direction direction) {
        snake.moveToDirection(direction);
    }

    public static class Builder extends PlayerBuilder<HumanPlayerImplementation> {
        @Override
        public HumanPlayerImplementation build() {
            if (name == null) {
                name = "Default_Player";
            }

            if (color == null) {
                color = Color.BLUE;
            }

            if (snake == null) {
                snake = new Snake.Builder().build();
            }

            return new HumanPlayerImplementation(this);
        }
    }
}
