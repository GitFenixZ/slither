package controller;

public class KeyboardController {
    private GridController controller;
    private GridView view;

    public KeyboardController(GridController controller) {
        this.controller = controller;
        this.view = controller.getView();

        initKeyboardController();
    }

    public void initKeyboardController() {
        view.getScene().setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    controller.movePlayer(controller.getPlayer(), Direction.UP);
                    break;
                case DOWN:
                    controller.movePlayer(controller.getPlayer(), Direction.DOWN);
                    break;
                case LEFT:
                    controller.movePlayer(controller.getPlayer(), Direction.LEFT);
                    break;
                case RIGHT:
                    controller.movePlayer(controller.getPlayer(), Direction.RIGHT);
                    break;
                default:
                    break;
            }
        });

    }

}
