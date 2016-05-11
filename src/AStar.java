import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by ste on 10/05/16.
 */
public class AStar {
    private PriorityQueue<Cell> open;
    private List<Cell> closed;
    /*
OPEN = priority queue containing START
CLOSED = empty set --means visited!!
OPEN U CLOSED means all visited nodes
we don't need a variable to keep track of visited nodes!!
while lowest rank in OPEN is not the GOAL:
  current = remove lowest rank item from OPEN
  add current to CLOSED
  for neighbors of current:
    cost = g(current) + movementcost(current, neighbor)
    if neighbor in OPEN and cost less than g(neighbor):
      remove neighbor from OPEN, because new path is better
    if neighbor in CLOSED and cost less than g(neighbor): ⁽²⁾
      remove neighbor from CLOSED
    if neighbor not in OPEN and neighbor not in CLOSED:
      set g(neighbor) to cost
      add neighbor to OPEN
      set priority queue rank to g(neighbor) + h(neighbor)
      set neighbor's parent to current

reconstruct reverse path from goal to start
by following parent pointers
     */

    //initialize open and closed
    AStar(){
        open = new PriorityQueue<>();
        closed = new ArrayList<>();
    }

    public void findPath(Grid grid, Cell start, Cell end){
        open.add(start);
        //while lowest rank in open not goal
        while(open.peek() != end){
            Cell current = open.poll();
            closed.add(current);
            int column = current.getColumn();
            int row = current.getRow();
            if(column - 1 >= 0){

            }
            if(column + 1 < grid.getColumns()){

            }
            if(row - 1 >= 0){

            }
            if(row + 1 < grid.getRows()){

            }
        }
    }


}
