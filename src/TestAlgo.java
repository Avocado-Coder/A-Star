public class TestAlgo {
    public static void main(String[] args){
        Grid grid = new Grid(5,5);
        grid.setStart(0);
        grid.setEnd(24);
        grid.setObstacle(4);
        grid.setObstacle(8);
        grid.setObstacle(16);
        grid.setObstacle(20);
        AStar aStar = new AStar();
        Cell end = aStar.findPath(grid);
        System.out.println("lol");
    }
}
