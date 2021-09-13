package edu.softwaredevelopment.labs.domain.ui;

public class InputPosition implements Position {
    private int x, y;

    public InputPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void incrementX(int value) {
        x += value;
    }

    public void incrementY(int value) {
        y += value;
    }
}
