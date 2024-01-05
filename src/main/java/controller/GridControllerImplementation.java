package controller;

import model.Direction;
import model.GridModel;
import model.GridModelImplementation;
import model.player.Bot;
import model.player.Human;
import model.player.Player;
import view.GridView;

public class GridControllerImplementation implements GridController {

    private GridModel model;
    private GridView view;

    public GridControllerImplementation() {
        initGame();
        initControllers();
    }

    private void initGame() {
        Player human = new Human.Builder().build();
        Player computer = new Bot.Builder().build();

        model = new GridModelImplementation(human, computer);
        view = new GridView(model);
    }

    private void initControllers() {
        BotController.initComputerController(model, view, this);
        HumanController.initKeyboardController(model, view, this);
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

        for (Player p : model.getPlayers()) {
            if (player.getSnake().collidedWith(p.getSnake())) {
                view.gameOver(p.getName());
                break;
            }
        }


        // TODO: Handle game over (Player died ?) to show game over view
    }
}
