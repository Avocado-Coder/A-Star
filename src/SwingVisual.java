import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Stack;

public class SwingVisual {

    public static void main(String[] args) {
        new SwingVisual(args);
    }

    public enum MouseState {NO_BUTTON, LEFT_BUTTON, RIGHT_BUTTON, MIDDLE_BUTTON}

    public SwingVisual(String[]args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                Grid grid;
                if(args.length == 2){
                    grid = new Grid(Integer.valueOf(args[1]), Integer.valueOf(args[0]));
                }else{
                    grid = new Grid();
                }

                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                JPanel gridPanel = new GridPanel(grid);
                frame.add(gridPanel, BorderLayout.NORTH);
                frame.add(new ButtonPanel(grid, gridPanel), BorderLayout.SOUTH);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                frame.getRootPane().setDefaultButton(null);
            }
        });
    }

    public class ButtonPanel extends JPanel{
        public ButtonPanel(Grid grid, JPanel gridPanel){
            JButton startSim = new JButton("Create Path");
            startSim.setFocusPainted(false);
            JButton stopSim = new JButton("Clear Path");
            stopSim.setFocusPainted(false);
            add(startSim);
            add(stopSim);
            startSim.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    AStar aStar = new AStar();
                    Cell end = aStar.findPath(grid);
                    Stack<Cell> stackPath = new Stack<>();
                    end = end.getParent();
                    while(end != grid.getStart()){
                        stackPath.push(end);
                        end = end.getParent();
                    }

                    while(!stackPath.isEmpty()){
                        grid.setPath(stackPath.pop());
                    }
                    gridPanel.repaint();
                }
            });

            stopSim.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    grid.resetPath();
                    gridPanel.repaint();
                }
            });
        }
    }

    public class GridPanel extends JPanel {

        private int columnCount = 25;
        private int rowCount = 25;

        private Point selectedCell;
        private MouseState mouseState = MouseState.NO_BUTTON;
        private Grid grid;

        public GridPanel(Grid grid) {
            this.grid = grid;
            this.columnCount = grid.getColumns();
            this.rowCount = grid.getRows();
            MouseAdapter mouseHandler;
            mouseHandler = new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if(SwingUtilities.isLeftMouseButton(e)) {
                        mouseState = MouseState.LEFT_BUTTON;
                    } else if(SwingUtilities.isRightMouseButton(e)) {
                        mouseState = MouseState.RIGHT_BUTTON;
                    } else if(SwingUtilities.isMiddleMouseButton(e)){
                        mouseState = MouseState.MIDDLE_BUTTON;
                    }

                    repaint();

                }

                @Override
                public void mouseMoved(MouseEvent e) {
                    Point point = e.getPoint();

                    int width = getWidth();
                    int height = getHeight();

                    int cellWidth = width / columnCount;
                    int cellHeight = height / rowCount;

                    selectedCell = null;

                    int column = (e.getX()/* - xOffset*/) / cellWidth;
                    int row = (e.getY()/* - yOffset*/) / cellHeight;

                    if (column >= 0 && row >= 0 && column < columnCount && row < rowCount) {

                        selectedCell = new Point(column, row);

                    }
                    if(e.getButton() == MouseEvent.BUTTON1)
                    {
                        System.out.println("Detected Mouse Left Click!");
                    }
                }
            };
            addMouseListener(mouseHandler);
            addMouseMotionListener(mouseHandler);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(800, 800);
        }

        //draw new rectangles when resized
        @Override
        public void invalidate() {
            grid.clearRectangles();
            selectedCell = null;
            super.invalidate();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();

            int width = getWidth();
            int height = getHeight();

            int cellWidth = width / columnCount;
            int cellHeight = height / rowCount;

            int xOffset = (width - (columnCount * cellWidth)) / 2;
            int yOffset = (height - (rowCount * cellHeight)) / 2;

            if (grid.getGrid().get(0).getRectangle() == null) {
                grid.initRectangle(cellWidth, cellHeight, xOffset, yOffset);
            }

            if (selectedCell != null) {

                int index = selectedCell.x + (selectedCell.y * columnCount);
                if(mouseState == MouseState.MIDDLE_BUTTON){
                    grid.setObstacle(index);
                    mouseState = MouseState.NO_BUTTON;
                }else if(mouseState == MouseState.LEFT_BUTTON){
                    grid.setStart(index);
                    mouseState = MouseState.NO_BUTTON;
                }else if(mouseState == MouseState.RIGHT_BUTTON){
                    grid.setEnd(index);
                    mouseState = MouseState.NO_BUTTON;
                }
            }
            grid.fillCells(g2d);
            g2d.dispose();
        }
    }
}