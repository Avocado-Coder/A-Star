import java.util.Stack;

public class TestAlgo {
    public static void main(String[] args){
        Grid grid = new Grid(25,25);
        grid.setStart(0);
        grid.setEnd(24);
//        grid.setObstacle(11);
//        grid.setObstacle(7);
//        grid.setObstacle(17);
//        grid.setObstacle(20);
        AStar aStar = new AStar();
        Cell end = aStar.findPath(grid);
        Stack<Cell> stackPath = new Stack<>();
        while(end != null){
            stackPath.push(end);
            end = end.getParent();
        }

        int i = 0;

        while(!stackPath.isEmpty()){
            Cell popped = stackPath.pop();
            System.out.println("Cell: " + i + "(" + popped.getColumn() + "," + popped.getRow() + ")");
            System.out.println(popped.getF());
        }
        System.out.println("lol");
    }
}
