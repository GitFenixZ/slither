package model.player;

import javafx.scene.paint.Color;
import model.snake.Snake;

public class Human extends PlayerImplementation {
    public Human(Builder builder) {
        super(builder);
    }

    @Override
    public PlayerBuilder<Human> getBuilder() {
        return new Builder();
    }


    public static class Builder extends PlayerBuilder<Human> {
        @Override
        public Human build() {
            if (name == null) {
                name = "Default_Player";
            }

            if (color == null) {
                color = Color.BLUE;
            }

            if (snake == null) {
                snake = new Snake.Builder().build();
            }

            return new Human(this);
        }
    }
}
