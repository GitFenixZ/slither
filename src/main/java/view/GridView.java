package view;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import model.GridModel;
import model.Segment;

import java.util.List;

public class GridView {

    public static final int CELL_SIZE = 40;
    private final Canvas canvas;
    private final GridModel model;
    private Scene scene;

    public GridView(GridModel model) {
        this.canvas = new Canvas();
        this.model = model;

        init();
        update();
    }

    private void init() {
        scene = new Scene(getPane(), model.getWidth() * GridView.CELL_SIZE, model.getHeight() * GridView.CELL_SIZE);
        canvas.setWidth(model.getWidth() * CELL_SIZE);
        canvas.setHeight(model.getHeight() * CELL_SIZE);
    }

    private StackPane getPane() {
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(canvas);
        return stackPane;
    }

    public void update() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        drawPlayer(gc);
    }

    private void drawPlayer(GraphicsContext gc) {
        List<Segment> segments = model.getPlayer().getSnake().getSegments();
        segments.forEach((Segment segment) -> {
                    gc.setFill(model.getPlayer().getColor());
                    gc.fillRect(segment.getX() * CELL_SIZE, segment.getY() * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
        );
    }

    public Scene getScene() {
        return scene;
    }

}
