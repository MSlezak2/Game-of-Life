package life.view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class UniverseViewPanel extends JPanel {

    private boolean[][] universeArray;

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        if (universeArray != null) {
            drawUniverse(g2d);
        }

    }

    void drawUniverse(Graphics2D g2d) {

        int cols = universeArray[0].length;
        int y = 0;                  // entity y coordinate
        int dy = getHeight() / cols; // height of the single entity
        int rows = universeArray.length;
        int x = 0;                   // entity x coordinate
        int dx = getWidth() / rows; // width of the single entity
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

    public void setUniverseArray(boolean[][] universeArray) {
        this.universeArray = universeArray;
    }

}
