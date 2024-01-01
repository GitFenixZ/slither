package controller;

import model.Direction;
import model.GridModel;
import model.GridModelImplementation;
import model.player.Player;

public class GridControllerImplementation implements GridController {

    private GridModel model;
    private GridView view;

    public GridControllerImplementation() {
        initGame();
        initKeyboardController();
    }

    private initGame() {
        model = new GridModelImplementation();
        view = new GridViewImplementation(model);
    }

    @Override
    public GridView getView() {
        return view;
    }

    @Override
    public void movePlayer(Player player, Direction direction) {

        if (model.movePlayer(player, direction)) {
            view.update();
        }

    }
}
