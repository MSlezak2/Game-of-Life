package life.view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class UniverseViewPanel extends JPanel {

    private boolean[][] universeArray;
    private int rows;   //how many rows and columns of cells the universe contains of
    private int cols;
    private int universeWidth;  //what size (in pixes) the universe has at the moment
    private int universeHeight;
    private int panelWidth;
    private int panelHeight;


    public UniverseViewPanel() {

    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        if (hasFrameBeenResized()) {
            calculateSizesOfCells();
        }

        if (universeArray != null) {
            drawUniverse(g2d);
        }

        //drawGrid(g2d,0,0,getWidth(),getHeight(),rows,cols);

    }

    /** drawGrid() method should be invoked after drawUniverse() method */
    private void drawGrid(Graphics2D g2d, int x0, int y0, int width, int height, int rows, int cols) {

        int x1,x2,y1,y2;

        g2d.setPaint(Color.gray);

        // drawing horizontal lines
        for (int i = 0; i <= rows; i++) {

            x1 = x0;
            y1 = i * height / rows;
            x2 = x0 + width;
            y2 = y1;
            g2d.drawLine(x1,y1,x2,y2);

        }

        // drawing vertical lines
        for (int i = 0; i <= rows; i++) {

            x1 = i * width / cols;
            y1 = y0;
            x2 = x1;
            y2 = y0 + height;
            g2d.drawLine(x1,y1,x2,y2);

        }

    }

    void drawUniverse(Graphics2D g2d) {

        int y = 0;                   // entity y coordinate
        int dy = Math.max(getHeight() / cols, 1); // height of the single entity
        int x = 0;                   // entity x coordinate
        int dx = Math.max(getWidth() / rows, 1); // width of the single entity
        Rectangle2D rectangle;

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {

                rectangle = new Rectangle2D.Double(x, y, dx, dy);

                if (universeArray[i][j] == true) {
                    g2d.setPaint(Color.black);
                } else {
                    g2d.setPaint(Color.white);
                }

                g2d.fill(rectangle);
                x += dx;

            }

            x = 0;
            y += dy;

        }

    }

    private boolean hasFrameBeenResized() {
        if (panelWidth != getWidth() || panelHeight != getHeight()) {
            panelWidth = getWidth();
            panelHeight = getHeight();
            System.out.println("function"+ panelWidth + " " + panelHeight);
            return true;
        }
            return false;
    }

    private void calculateSizesOfCells() {
        
    }

    public void setUniverseArray(boolean[][] universeArray) {
        this.universeArray = universeArray;
        rows = universeArray.length;
        cols = universeArray[0].length;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}