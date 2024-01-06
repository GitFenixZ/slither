package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.GridModel;
import model.Segment;
import model.player.Player;

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

    /**
     * Initializes the GridView
     */
    private void init() {
        // Create the scene
        scene = new Scene(
                getPane(),
                model.getWidth() * GridView.CELL_SIZE,
                model.getHeight() * GridView.CELL_SIZE);

        // Set the canvas dimensions
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
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); // clear the canvas

        for (Player player : model.getPlayers()) {
            drawPlayer(gc, player);
        }
        drawFood(gc);
    }

    private void drawPlayer(GraphicsContext gc, Player player) {
        List<Segment> segments = player.getSnake().getSegments();
        segments.forEach((Segment segment) -> {
                    gc.setFill(player.getColor());
                    gc.fillRect(
                            segment.getX() * CELL_SIZE,
                            segment.getY() * CELL_SIZE,
                            CELL_SIZE,
                            CELL_SIZE);

                    if (player.getSnake().getHead().equals(segment)) {
                        gc.setStroke(Color.GOLD);
                    } else {
                        gc.setStroke(Color.BLACK);
                    }
                    gc.setLineWidth(2.5);
                    gc.strokeRect(
                            segment.getX() * CELL_SIZE,
                            segment.getY() * CELL_SIZE,
                            CELL_SIZE,
                            CELL_SIZE);

                    String arrow;
                    switch (segment.getDirection()) {
                        case UP -> arrow = "↑";
                        case DOWN -> arrow = "↓";
                        case LEFT -> arrow = "←";
                        case RIGHT -> arrow = "→";
                        default -> arrow = "X";
                    }

                    // Draw the direction of the segment
                    gc.setFill(Color.BLACK);
                    gc.fillText(arrow,
                            segment.getX() * CELL_SIZE + CELL_SIZE / 2,
                            segment.getY() * CELL_SIZE + CELL_SIZE / 2);
                }
        );
    }

    private void drawFood(GraphicsContext gc) {
        gc.setFill(Color.PURPLE);
        gc.fillOval(
                model.getFoodCoordinates().getX() * CELL_SIZE,
                model.getFoodCoordinates().getY() * CELL_SIZE,
                CELL_SIZE,
                CELL_SIZE);

    }

    public Scene getScene() {
        return scene;
    }

    public void gameOver(String winner) {
        update();
        StackPane stackPane = (StackPane) scene.getRoot();

        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);

        Label gameOverLabel = new Label("Game Over");
        Label winnerLabel = new Label(winner + " wins");

        gameOverLabel.setFont(new Font(80));
        winnerLabel.setFont(new Font(40));

        vbox.getChildren().add(gameOverLabel);
        vbox.getChildren().add(winnerLabel);

        stackPane.getChildren().add(vbox);
    }

}
