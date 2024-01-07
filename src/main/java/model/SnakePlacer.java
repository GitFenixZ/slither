package model;

import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SnakePlacer {

    private static final List<Point2D> startingPositions =
            new ArrayList<>(Arrays.asList(new Point2D(
                            (int) GridModelImplementation.WIDTH / 4,
                            (int) GridModelImplementation.HEIGHT / 2
                    ),
                    new Point2D(
                            (int) GridModelImplementation.WIDTH / 2,
                            (int) GridModelImplementation.HEIGHT / 2
                    ),
                    new Point2D(
                            (int) GridModelImplementation.WIDTH / 4 * 3,
                            (int) GridModelImplementation.HEIGHT / 2
                    )
            ));

    private static int index = 0;

    /**
     * Gets the next starting position for a snake
     *
     * @return the next starting position for a snake
     * @throws ArrayIndexOutOfBoundsException if the maximum number of player has already been reached
     */
    public static Point2D getNextStartingPosition() throws ArrayIndexOutOfBoundsException {
        if (index < startingPositions.size()) {
            Point2D tmp = startingPositions.get(index);
            index++;
            return tmp;
        }
        throw new ArrayIndexOutOfBoundsException("Player limit attained");
    }
}
