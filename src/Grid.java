//Keep the Grid's state.  If the state changes due to a mouse click or A star algorithm, update the grid

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Grid {
    private List<Cell> grid;
    private int columns;
    private int rows;
    private Cell start;
    private Cell end;
    private Cell path;

    //default number of columns and rows is 5
    Grid(){
        this.columns = 25;
        this.rows = 25;
        initState();
    }

    Grid(int columns, int rows){
        this.columns = columns;
        this.rows = rows;
        initState();
    }

    private void initState(){
        grid = new ArrayList<>();
        for(int row = 0; row < rows; row++){
            for(int col = 0; col< columns; col++){
                Cell cell = new Cell();
                cell.setState(Cell.State.EMPTY);
                cell.setRow(row);
                cell.setColumn(col);
                grid.add(cell);
            }
        }
    }

    public void initRectangle(int cellWidth, int cellHeight, int xOffset, int yOffset){
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                int index = col + (row * columns);
                Rectangle rectangle = new Rectangle(
                        xOffset + (col * cellWidth),
                        yOffset + (row * cellHeight),
                        cellWidth,
                        cellHeight);
                grid.get(index).setRectangle(rectangle);
            }
        }
    }

    public List<Cell> getGrid() {
        return grid;
    }

    public void setStart(int index){
        if(start != null){
            this.getStart().setState(Cell.State.EMPTY);
        }
        Cell newStart = grid.get(index);
        newStart.setState(Cell.State.START);
        start = newStart;
    }

    public void setEnd(int index){
        if(end != null){
            this.getEnd().setState(Cell.State.EMPTY);
        }
        Cell newEnd = grid.get(index);
        newEnd.setState(Cell.State.END);
        end = newEnd;
    }

    public void setObstacle(int index){
        grid.get(index).setState(Cell.State.OBSTACLE);
    }

    public Cell getCell(int row, int col){
        int index = col + (row * columns);
        return grid.get(index);
    }

    public void clearRectangles(){
        for(Cell cell : grid){
            cell.setRectangle(null);
        }
    }

    public void fillCells(Graphics2D g2d){
        for(Cell cell : grid){
            if(cell.getState() == Cell.State.START){
                g2d.setColor(Color.GREEN);
                g2d.fill(cell.getRectangle());
            }else if(cell.getState() == Cell.State.END){
                g2d.setColor(Color.RED);
                g2d.fill(cell.getRectangle());
            }else if(cell.getState() == Cell.State.OBSTACLE){
                g2d.setColor(Color.BLACK);
                g2d.fill(cell.getRectangle());
            }else if(cell.getState() == Cell.State.PATH){
                g2d.setColor(Color.BLUE);
                g2d.fill(cell.getRectangle());
            }
        }

        //draw the edges of grid
        g2d.setColor(Color.GRAY);
        for(Cell cell : grid){
            g2d.draw(cell.getRectangle());
        }
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    public Cell getStart() {
        return start;
    }

    public Cell getEnd() {
        return end;
    }

    public Cell getPath() {
        return path;
    }

    public void setPath(Cell path) {
        this.path = path;
        path.setState(Cell.State.PATH);
    }
}
