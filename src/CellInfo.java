//This class will keep the state of the grid

import java.awt.*;

public class CellInfo {
    public enum CellState {EMPTY, START, END, PATH, OBSTACLE}
    private CellState cellState;
    private Rectangle rectangle;

    public CellState getCellState() {
        return cellState;
    }

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
}
