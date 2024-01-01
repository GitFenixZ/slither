package controller;

import model.Direction;
import model.GridModel;
import model.GridModelImplementation;
import model.player.Player;
import model.player.PlayerImplementation;

public class GridControllerImplementation implements GridController {

    private GridModel model;
    private GridView view;
    private KeyboardController keyboardController;

    public GridControllerImplementation() {
        initGame();
        initController();
    }

    public void initController() {
        keyboardController = new KeyboardController(this);
    }

    private void initGame() {
        Player player = new PlayerImplementation.Builder().build();

        model = new GridModelImplementation(player);
        view = new GridViewImplementation(model);
    }

    @Override
    public GridView getView() {
        return view;
    }

    @Override
    public void movePlayer(Player player, Direction direction) {
        // TODO: if game is over, do nothing

        if (model.movePlayer(player, direction)) {
            view.update();
        }

        // TODO: Handle game over (Player died ?)
    }
}
