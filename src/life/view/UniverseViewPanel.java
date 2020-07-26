package life.view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;

/** Hint: In order to update the view of the current generation
 * with 'repaint()' method you need to update 'universe' field first
 */

public class UniverseViewPanel extends JPanel {

    private boolean[][] universe;

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        if (universe != null) {
            drawUniverse(universe, g2d);
        }

    }

    public void drawUniverse(boolean[][] universe, Graphics2D g2d) {

        int cols = universe[0].length;
        int y = 0;                  // entity y coordinate
        int dy = getHeight() / cols; // height of the single entity
        int rows = universe.length;
        int x = 0;                   // entity x coordinate
        int dx = getWidth() / rows; // width of the single entity
        Rectangle2D rectangle;

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {

                rectangle = new Rectangle2D.Double(x, y, dx, dy);

                if (universe[i][j] == true) {
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

    public void setUniverse(boolean[][] universe) {
        this.universe = universe;
    }

}
