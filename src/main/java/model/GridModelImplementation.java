package model;

public class GridModelImplementation implements GridModel {

    private static final int WIDTH = 32;
    private static final int HEIGHT = 18;

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }
}
