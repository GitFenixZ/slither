package model;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<Segment> segments;

    Snake() {
        segments = new ArrayList<>();
        segments.add(new BasicSegment(0, 0));
    }

    public void moveToPosition(int x, int y) {
        int nextDestX = x;
        int nextDestY = y;
        for (Segment s : segments) {
            int tempX = s.getCurrentX();
            int tempY = s.getCurrentY();
            s.moveToPosition(nextDestX, nextDestY);
            nextDestX = tempX;
            nextDestY = tempY;
        }
    }
}
