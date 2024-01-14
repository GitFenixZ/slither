import controller.grid.GridController;
import controller.grid.GridControllerImplementation;
import javafx.application.Application;
import javafx.stage.Stage;
import view.grid.GridView;

public class Launcher extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        // Create the controller
        GridController controller = new GridControllerImplementation();
        GridView view = controller.getView();

        // Set up the stage with the initialized view
        primaryStage.setScene(view.getScene());
        primaryStage.setTitle("Game");
        primaryStage.show();
    }
}
