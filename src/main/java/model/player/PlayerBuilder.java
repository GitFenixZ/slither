package model.player;

import javafx.scene.paint.Color;
import model.snake.Snake;

/**
 * The abstract class PlayerBuilder is a class following the Builder design
 * pattern.
 * It is used to construct instances of Player subclasses.
 *
 * @param <T> the type of player to be built
 * @apiNote Subclasses must implement the {@link #build()} method to create the
 * wanted player object
 */
public abstract class PlayerBuilder<T extends PlayerImplementation> {
    protected Color color;
    protected String name;
    protected Snake snake;

    /**
     * Sets the color of the player.
     *
     * @param color the color of the player
     * @return the player builder instance
     */
    public PlayerBuilder<T> color(Color color) {
        this.color = color;
        return this;
    }

    /**
     * Sets the name of the player.
     *
     * @param name the name of the player
     * @return the player builder instance
     */
    public PlayerBuilder<T> name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the snake of the player by deep copying the given snake.
     *
     * @param snake the snake of the player
     * @return the player builder instance
     */
    public PlayerBuilder<T> snake(Snake snake) {
        this.snake = snake.copy();
        return this;
    }

    /**
     * Builds the player subclass object.
     *
     * @return the built player subclass object
     * @apiNote DO NOT FORGET to handle default values.
     */
    public abstract T build();
}
