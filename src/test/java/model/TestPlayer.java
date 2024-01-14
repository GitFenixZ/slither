package model;

import javafx.geometry.Point2D;
import model.player.Player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.*;

public class TestPlayer {

    private Player player;

    @BeforeEach
    void setUp() {
        player = spy(Player.class);
    }

    @Test
    void grow_behavior_inOrder_whenGetSnakeReturnsNonNull() {
        Snake snake = mock(Snake.class);
        doReturn(snake).when(player).getSnake();

        player.grow();

        InOrder inorder = inOrder(player, snake);

        inorder.verify(player, times(2)).getSnake();
        inorder.verify(snake, times(1)).grow();
    }

    @Test
    void grow_behavior_inOrder_whenGetSnakeReturnsNull() {
        Snake snake = mock(Snake.class);
        doReturn(null).when(player).getSnake();

        player.grow();

        InOrder inorder = inOrder(player, snake);

        inorder.verify(player, times(1)).getSnake();
        inorder.verify(snake, never()).grow();
    }

    @Test
    void extractCoordinates_whenDestinationIsNull() {
        List<Segment> snake_segments = List.of(
                new BasicSegment(new Point2D(0, 0), Direction.RIGHT),
                new BasicSegment(new Point2D(1, 0), Direction.RIGHT),
                new BasicSegment(new Point2D(2, 0), Direction.RIGHT)
        );

        Snake player_snake = new Snake.Builder().segments(snake_segments).build();
        doReturn(player_snake).when(player).getSnake();

        List<Point2D> result = new ArrayList<>();
        player.extractCoordinates(result);

        List<Point2D> expected = snake_segments.stream().map(Segment::getCoordinates).toList();

        assertIterableEquals(expected, result);
    }
}
