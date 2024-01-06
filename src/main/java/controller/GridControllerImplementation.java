package controller;

import model.Direction;
import model.GridModel;
import model.GridModelImplementation;
import model.player.Bot;
import model.player.Human;
import model.player.Player;
import view.GridView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class GridControllerImplementation implements GridController {
    private Phase phase;
    private List<HumanPlayerImplementation> humanPlayers;
    private List<ComputerPlayerImplementation> computerPlayers;

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

        Iterator<Player> iter = model.getPlayers().iterator();
        while (iter.hasNext()) {
            Player p = iter.next();
            if (!p.equals(player) && player.getSnake().collidedWith(p.getSnake())) {
                model.getPlayers().remove(player);
                if (model.getPlayers().size() == 1) {
                    view.gameOver(model.getPlayers().get(0).getName());
                    phase = Phase.GAME_OVER;
                    break;
                }
            }
        }
    }
}
