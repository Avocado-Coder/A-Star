//This class will keep the state of the grid

import java.awt.*;

public class Cell {
    public enum State {EMPTY, START, END, PATH, OBSTACLE}
    private State state;
    private Rectangle rectangle;
    private int column;
    private int row;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
}
