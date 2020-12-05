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
    private boolean paused = true;

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

        //TODO: clean the placing of the buttons in GUI
        JButton pauseButton = new JButton("PAUSE");
        pauseButton.addActionListener(e -> paused = !paused);
        add(pauseButton);

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
        universePanel.repaint();

        // setting the minimum size of the frame (to prevent cropping view of the universe)
        calculateMinWidth();
        calculateMinHeight();
        setMinimumSize(new Dimension(minWidth,minHeight));
    }

    private void calculateMinWidth(){
        // the width of the window couldn't be smaller than the number of
        // columns in the universe multiplied by 2 and then added to 1
        // (because of the grid)
        minWidth = 2 * universePanel.getCols() + 1;
    }

    private void calculateMinHeight(){
        // the height of the window couldn't be smaller than the number of
        // rows in the universe (+ the grid) and the height of detailsPanel
        // combined
        minHeight = 2 * universePanel.getRows() + 1 + detailsPanel.getHeight();
    }

    public boolean isPaused(){
        return paused;
    }
}
