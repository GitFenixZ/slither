import controller.GridController;
import controller.GridControllerImplementation;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.GridModel;
import view.GridView;

public class Launcher extends Application {

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

    public static void main(String[] args) {
        launch();
    }
}
