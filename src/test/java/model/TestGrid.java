package model;

import javafx.geometry.Point2D;
import model.food.Food;
import model.player.Bot;
import model.player.Human;
import model.player.Player;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.spy;

class TestGrid {
    @Test
    void isInsideGrid_Valid() {
        GridModel grid = spy(new TestGridModel());
        for (int row = 0; row < grid.getHeight(); row++) {
            for (int col = 0; col < grid.getWidth(); col++) {
                Point2D coordinates = new Point2D(row, col);
                assertTrue(grid.isInsideGrid(coordinates));
            }
        }
    }

    @Test
    void isInsideGrid_Invalid() {
        GridModel grid = spy(new TestGridModel());
        for (int row = -10; row <= grid.getHeight() + 10; row++) {
            for (int col = -10; col <= grid.getWidth() + 10; col++) {
                if (row >= 0 && row < grid.getHeight() && col >= 0 && col < grid.getWidth()) {
                    continue;
                }
                Point2D coordinates = new Point2D(row, col);
                assertFalse(grid.isInsideGrid(coordinates));
            }
        }
    }

    private static class TestGridModel implements GridModel {
        private static final int WIDTH = 25;
        private static final int HEIGHT = 25;

        @Override
        public Phase getPhase() {
            return null;
        }

        @Override
        public void setPhase(Phase phase) {
        }

        @Override
        public List<Player> getPlayers() {
            return null;
        }

        @Override
        public List<Human> getHumanPlayers() {
            return null;
        }

        @Override
        public List<Bot> getComputerPlayers() {
            return null;
        }

        @Override
        public int getWidth() {
            return WIDTH;
        }

        @Override
        public int getHeight() {
            return HEIGHT;
        }

        @Override
        public Food getFood() {
            return null;
        }
    }
}
