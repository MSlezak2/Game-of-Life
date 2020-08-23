package life.view;

import life.model.Universe;

import javax.swing.*;
import java.awt.*;

public class GameOfLife extends JFrame {

    private JPanel detailsPanel;
    private UniverseViewPanel universePanel;
    private JLabel generationNumberLabel;
    private JLabel aliveNumberLabel;
    private int minWidth = 400;
    private int minHeight = 500;

    public GameOfLife(int windowWidth, int windowHeight) {

        setupFrame(windowWidth, windowHeight);
        setupDetailsPanel();
        setupUniversePanel();
        repaint();      // without those lines
        revalidate();   // gui is displayed unpredictably

    }

    private void setupFrame(int width, int height) {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        setMinimumSize(new Dimension(minWidth, minHeight));
        setSize(width,height);
        setVisible(true);

    }

    private void setupDetailsPanel() {

        detailsPanel = new JPanel(new GridLayout(2, 1));
        detailsPanel.setBackground(new Color(150, 200, 200));
        detailsPanel.setPreferredSize(new Dimension(getWidth(),(int) (getHeight() * 0.1)));

        generationNumberLabel = new JLabel("Generation #");
        detailsPanel.add(generationNumberLabel);

        aliveNumberLabel = new JLabel("Alive: ");
        detailsPanel.add(aliveNumberLabel);

        add(detailsPanel);

    }

    private void setupUniversePanel() {

        universePanel = new UniverseViewPanel();
        universePanel.setBackground(new Color(150,175,150));
        universePanel.setPreferredSize(new Dimension(getWidth(),(int) (getHeight() * 0.9)));

        add(universePanel);

    }

    public void drawUniverse(Universe universe) {

        generationNumberLabel.setText("Generation #" + universe.getGenerationNumber());
        aliveNumberLabel.setText("Alive: " + universe.getAliveNumber());
        universePanel.setUniverseArray(universe.getCurrentGeneration());
//        int universeImageWidth = universePanel.getCols() * (Math.max(getWidth() / universePanel.getCols(), 1)); // width of the single entity
//        int universeImageHeight = universePanel.getRows() * (Math.max(getHeight() / universePanel.getRows(), 1)); // height of the single entity
//        universePanel.setSize(universeImageWidth,universeImageHeight);
//        setSize(universeImageWidth + 6,getHeight());
        universePanel.repaint();
        System.out.println("Frame"+getSize());
        System.out.println("panel"+universePanel.getSize());
//        pack();

    }

}

