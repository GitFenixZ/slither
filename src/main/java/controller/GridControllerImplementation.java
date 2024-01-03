package controller;

import model.Direction;
import model.GridModel;
import model.GridModelImplementation;
import model.player.ComputerImplementation;
import model.player.Player;
import model.player.PlayerImplementation;
import view.GridView;

public class GridControllerImplementation implements GridController {

    private GridModel model;
    private GridView view;

    public GridControllerImplementation() {
        initGame();
        initControllers();
    }

    private void initGame() {
        Player player = new PlayerImplementation.Builder().build();
        Player computer = new ComputerImplementation.Builder().build();

        model = new GridModelImplementation(player, computer);
        view = new GridView(model);
    }

    private void initControllers() {
        ComputerController.initComputerController(model, view, this);
        KeyboardController.initKeyboardController(model, view, this);
    }

    @Override
    public GridModel getModel() {
        return model;
    }

    @Override
    public GridView getView() {
        return view;
    }

    @Override
    public void movePlayer(Player player, Direction direction) {
        // TODO: If game is over, do nothing

        if (model.movePlayer(player, direction)) {
            view.update();
        }

        // TODO: Handle game over (Player died ?) to show game over view
    }
}
