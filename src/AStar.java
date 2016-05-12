import java.util.*;

/**
 * Created by ste on 10/05/16.
 */
public class AStar {
    private Queue<Cell> open;
    private List<Cell> closed;

    //initialize open and closed
    AStar(){
        Comparator<Cell> comparator = new AStarCompare();
        open = new PriorityQueue<>(10, comparator);
        closed = new ArrayList<>();
    }

    public Cell findPath(Grid grid){
        Cell start = grid.getStart();
        start.setG(0D);
        Cell end = grid.getEnd();
        open.add(start);
        //while lowest rank in open not goal
        while(open.peek() != end){
            Cell current = open.poll();
            closed.add(current);
            int column = current.getColumn();
            int row = current.getRow();
            if(column - 1 >= 0){
                analyzeNeighbour(current, grid.getCell(row, column - 1), start, end);
            }
            if(column + 1 < grid.getColumns()){
                analyzeNeighbour(current, grid.getCell(row, column + 1), start, end);
            }
            if(row - 1 >= 0){
                analyzeNeighbour(current, grid.getCell(row - 1, column), start, end);
            }
            if(row + 1 < grid.getRows()){
                analyzeNeighbour(current, grid.getCell(row + 1, column), start, end);
            }
        }
        return end;
    }

    private void analyzeNeighbour(Cell current, Cell neighbour, Cell start, Cell end){
//        double dx = Math.abs(neighbour.getRow() - start.getRow());
//        double dy = Math.abs(neighbour.getColumn() - start.getColumn());
        Double cost = current.getG() + neighbour.getState().getCost();
        //remove existing neighbour that sucks and replace with better path
        if(open.contains(neighbour) && cost < neighbour.getG()){
            open.remove(neighbour);
        }
        if(closed.contains(neighbour) && cost < neighbour.getG()){
            closed.remove(neighbour);
//            return;
        }
        if(!open.contains(neighbour) && !closed.contains(neighbour)){
            neighbour.setG(cost);
            int dxEnd = Math.abs(neighbour.getRow() - end.getRow());
            int dyEnd = Math.abs(neighbour.getColumn() - end.getColumn());
//            neighbour.setH(Math.sqrt(Math.pow(dxEnd, 2) + Math.pow(dyEnd,2)));
            neighbour.setH((dxEnd + dyEnd)*1.001);
            neighbour.setF(neighbour.getH() + neighbour.getG());

            neighbour.setParent(current);
            open.offer(neighbour);
        }
    }

    private class AStarCompare implements Comparator<Cell> {

        public int compare(Cell a, Cell b) {
            if (a.getF() < b.getF()) return -1;
            if (a.getF() > b.getF()) return 1;
            return 0;
        }
    }
}
