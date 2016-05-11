//This class will keep the state of the grid

import java.awt.*;

public class Cell {
    public enum State {
        EMPTY(1), START(0), END(0), OBSTACLE(Integer.MAX_VALUE);
        private int cost;

        State(int cost) {
            this.cost = cost;
        }

        public int getCost() {
            return cost;
        }
    }
    private State state;
    private Rectangle rectangle;
    private int column;
    private int row;
    private double g;  // g is distance from the source
    private double h;  // h is the heuristic of destination.
    private double f;  // f = g + h
    private Cell parent;

    Cell(){
        state = State.EMPTY;
        g = Double.MAX_VALUE;
//        f = Double.MAX_VALUE;
    }

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

    public double getG() {
        return g;
    }

    public void setG(Double g) {
        this.g = g;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    public double getF() {
        return f;
    }

    public void setF(double f) {
        this.f = f;
    }

    public Cell getParent() {
        return parent;
    }

    public void setParent(Cell parent) {
        this.parent = parent;
    }
}
