public class TestAlgo {
    public static void main(String[] args){
        Grid grid = new Grid(5,5);
        grid.setStart(0);
        grid.setEnd(24);
        grid.setObstacle(4);
        grid.setObstacle(12);
        grid.setObstacle(15);
        AStar aStar = new AStar();
        Cell end = aStar.findPath(grid, grid.getCell(0,0), grid.getCell(4,4));
        System.out.print("lkfesjjewjfoijo");
    }
}
