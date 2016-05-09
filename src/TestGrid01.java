import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class TestGrid01 {

    public static void main(String[] args) {
        new TestGrid01();
    }

    public enum MouseState {NO_BUTTON, LEFT_BUTTON, RIGHT_BUTTON}

    public enum ProgramState {ADD_START, ADD_END, ADD_OBSTACLES, PATH_FINDING}

    public TestGrid01() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(new TestPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class TestPane extends JPanel {

        private int columnCount = 5;
        private int rowCount = 5;

        private List<Rectangle> cells;
        private Point selectedCell;
        private MouseState mouseState = MouseState.NO_BUTTON;
        private ProgramState programState = ProgramState.ADD_START;
        private Grid grid;

        public TestPane() {
            cells = new ArrayList<>(columnCount * rowCount);
            grid = new Grid(columnCount, rowCount);
            MouseAdapter mouseHandler;
            mouseHandler = new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if(e.getButton() == MouseEvent.BUTTON1)
                    {
                        mouseState = MouseState.LEFT_BUTTON;
                        System.out.println("Detected Mouse Left Click!");
                    }
                    else if(e.getButton() == MouseEvent.BUTTON3)
                    {
                        mouseState = MouseState.RIGHT_BUTTON;
                        System.out.println("Detected Mouse Right Click!");
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
            return new Dimension(200, 200);
        }

        //I don't know when this function is called
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
                if(mouseState == MouseState.NO_BUTTON){
                    g2d.setColor(Color.BLUE);
                }else if(mouseState == MouseState.LEFT_BUTTON){
                    g2d.setColor(Color.GREEN);
                    grid.setStart(index);
                    mouseState = MouseState.NO_BUTTON;
                }else if(mouseState == MouseState.RIGHT_BUTTON){
                    g2d.setColor(Color.RED);
                    mouseState = MouseState.NO_BUTTON;
                }
                grid.fillCells(g2d);

            }

//            g2d.dispose();
        }
    }
}