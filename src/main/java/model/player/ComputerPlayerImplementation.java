package model.player;

import javafx.scene.paint.Color;
import model.Direction;
import model.Snake;

public class ComputerPlayerImplementation implements Player {

    private final Color color;
    private final String name;
    private final Snake snake;

    public ComputerPlayerImplementation(ComputerPlayerImplementation.Builder builder) {
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

    public static class Builder extends PlayerBuilder<ComputerPlayerImplementation> {
        @Override
        public ComputerPlayerImplementation build() {
            if (name == null) {
                name = "CPU";
            }

            if (color == null) {
                color = Color.RED;
            }

            if (snake == null) {
                snake = new Snake.Builder().build();
            }

            return new ComputerPlayerImplementation(this);
        }
    }
}
