//Keep the Grid's state.  If the state changes due to a mouse click or A star algorithm, update the grid

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Grid {
    private List<CellInfo> grid;
    private int columns;
    private int rows;

    //default number of columns and rows is 5
    Grid(){
        this.columns = 5;
        this.rows = 5;
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
                CellInfo cellInfo = new CellInfo();
                cellInfo.setCellState(CellInfo.CellState.EMPTY);
                grid.add(cellInfo);
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

    public List<CellInfo> getGrid() {
        return grid;
    }

    public void setStart(int index){
        grid.get(index).setCellState(CellInfo.CellState.START);
    }

    public void setEnd(int index){
        grid.get(index).setCellState(CellInfo.CellState.END);
    }

    public void setObstacle(int index){
        grid.get(index).setCellState(CellInfo.CellState.OBSTACLE);
    }

    public void clearRectangles(){
        for(CellInfo cellInfo : grid){
            cellInfo.setRectangle(null);
        }
    }

    public void fillCells(Graphics2D g2d){
        for(CellInfo cellInfo : grid){
            if(cellInfo.getCellState() == CellInfo.CellState.START){
                g2d.setColor(Color.GREEN);
                g2d.fill(cellInfo.getRectangle());
            }else if(cellInfo.getCellState() == CellInfo.CellState.END){
                g2d.setColor(Color.RED);
                g2d.fill(cellInfo.getRectangle());
            }else if(cellInfo.getCellState() == CellInfo.CellState.OBSTACLE){
                g2d.setColor(Color.BLACK);
                g2d.fill(cellInfo.getRectangle());
            }
        }

        //draw the edges of grid
        g2d.setColor(Color.GRAY);
        for(CellInfo cellInfo : grid){
            g2d.draw(cellInfo.getRectangle());
        }
    }
}
