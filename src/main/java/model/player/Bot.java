package model.player;

import javafx.scene.paint.Color;
import model.Snake;

public class Bot extends PlayerImplementation {
    public Bot(Builder builder) {
        super(builder);
    }

    @Override
    public PlayerBuilder<Bot> getBuilder() {
        return new Builder();
    }

    public static class Builder extends PlayerBuilder<Bot> {
        @Override
        public Bot build() {
            if (name == null) {
                name = "CPU";
            }

            if (color == null) {
                color = Color.RED;
            }

            if (snake == null) {
                snake = new Snake.Builder().build();
            }

            return new Bot(this);
        }
    }
}
