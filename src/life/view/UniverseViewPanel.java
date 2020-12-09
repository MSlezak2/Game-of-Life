package life.view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

class UniverseViewPanel extends JPanel {

    private boolean[][] universeArray;
    private int rows;   //how many rows and columns of cells the universe contains of
    private int cols;
    private int universeWidth;  //what size (in pixes) the universe has at the moment
    private int universeHeight;
    private int panelWidth;
    private int panelHeight;
    private int cellWidth;
    private int cellHeight;
    private int leftUpperCornerX;
    private int leftUpperCornerY;


    public UniverseViewPanel() {

        panelWidth = getWidth();
        panelHeight = getHeight();

        setBackground(new Color(0xb3,0xe5,0xfc));

    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        super.paintComponent(g); //without that line there's a mess

        if (universeArray != null) {

            if (hasFrameBeenResized()) {

                calculateSizeOfCells();

            }

            drawUniverse(g2d);

            drawGrid(g2d);

        }

    }

    /** drawGrid() method should be invoked after drawUniverse() method */
    private void drawGrid(Graphics2D g2d) {

        int x1,x2,y1,y2;

        g2d.setPaint(Color.gray);

        // drawing horizontal lines
        for (int i = 0; i <= rows; i++) {

            x1 = leftUpperCornerX;
            y1 = leftUpperCornerY + i * cellHeight;
            x2 = leftUpperCornerX + universeWidth;
            y2 = y1;
            g2d.drawLine(x1,y1,x2,y2);

        }

        // drawing vertical lines
        for (int i = 0; i <= rows; i++) {

            x1 = leftUpperCornerX + i * cellWidth;
            y1 = leftUpperCornerY;
            x2 = x1;
            y2 = leftUpperCornerY + universeHeight;
            g2d.drawLine(x1,y1,x2,y2);

        }

    }

    void drawUniverse(Graphics2D g2d) {

        int y = leftUpperCornerY;                   // entity y coordinate
        int x = leftUpperCornerX;                   // entity x coordinate
        Rectangle2D rectangle;

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {

                rectangle = new Rectangle2D.Double(x, y, cellWidth, cellHeight);

                if (universeArray[i][j] == true) {
                    g2d.setPaint(Color.black);
                } else {
                    g2d.setPaint(Color.white);
                }

                g2d.fill(rectangle);
                x += cellWidth;

            }

            x = leftUpperCornerX;
            y += cellHeight;

        }

    }

    private boolean hasFrameBeenResized() {
        if (panelWidth != getWidth() || panelHeight != getHeight()) {
            panelWidth = getWidth();
            panelHeight = getHeight();
            return true;
        }
        return false;
    }

    void calculateSizeOfCells() {

        // computing the size (in pixels) of the universe and single cell
        int tempUniverseWidth = Math.min(panelWidth,panelHeight);
        int tempUniverseHeight = Math.min(panelWidth,panelHeight);
        cellWidth = Math.max(2,tempUniverseWidth / cols);
        cellHeight = Math.max(2,tempUniverseHeight / rows);
        universeWidth = cols * cellWidth;
        universeHeight = rows * cellHeight;
        leftUpperCornerX = (panelWidth - universeWidth) / 2;
        leftUpperCornerY = (panelHeight - universeHeight) / 2;

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
