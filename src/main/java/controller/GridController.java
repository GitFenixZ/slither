package controller;

import model.Direction;
import model.GridModel;
import model.player.Player;
import view.GridView;

public interface GridController {

    GridModel getModel();

    GridView getView();

    void movePlayer(Player player, Direction direction);

}
