package controller;

import model.Direction;
import model.player.Player;

public interface GridController {

    GridView getView();

    void movePlayer(Player player, Direction direction);

}
