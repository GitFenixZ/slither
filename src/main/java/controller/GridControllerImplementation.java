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
    private List<Human> humanPlayers;
    private List<Bot> computerPlayers;

    private GridModel model;
    private GridView view;

    public GridControllerImplementation() {
        initGame();
        initControllers();
    }

    private void initGame() {
        Human human = new Human.Builder().build();
        List<Human> humanPlayers = new ArrayList<>(Arrays.asList(human));

        Bot computer = new Bot.Builder().build();
        List<Bot> computerPlayers = new ArrayList<>(Arrays.asList(computer));

        model = new GridModelImplementation(humanPlayers, computerPlayers);
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
        if (model.getPhase() == GridModel.Phase.PLAYING) {

            if (model.movePlayer(player, direction)) {
                view.update();
            }

            Iterator<Player> iter = model.getPlayers().iterator();
            while (iter.hasNext()) {
                Player p = iter.next();
                if (player.getSnake().collidedWith(p.getSnake())) {
                    model.getPlayers().remove(player);
                    if (model.getPlayers().size() == 1) {
                        view.gameOver(model.getPlayers().get(0).getName());
                        model.setPhase(GridModel.Phase.GAME_OVER);
                        break;
                    }
                }
            }
        }
    }
}
