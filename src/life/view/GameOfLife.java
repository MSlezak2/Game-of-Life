package life.view;

import javax.swing.*;

public class GameOfLife extends JFrame {

    JPanel detailPanel;
    JPanel universePanel;
    int frameWidth = 600;
    int framwHeight = 700;

    public GameOfLife() {

        setupFrame(frameWidth,framwHeight);

    }

    private void setupFrame(int width, int height) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width,height);
        setVisible(true);
    }

}
