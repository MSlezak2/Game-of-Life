package life.view;

import life.model.Universe;

import javax.swing.*;

import java.awt.*;
import java.util.Hashtable;

public class GameOfLife extends JFrame {

    private JPanel detailsPanel;
    private UniverseViewPanel universePanel;
    private JLabel generationNumberLabel;
    private JLabel aliveNumberLabel;
    private int minWidth;
    private int minHeight;
    private boolean paused = true;
    private int requestedSpeed = 50; // the higher the smaller amount of time between iterations
    private boolean restartRequested = true;
    private int requestedSize = 50;

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

        detailsPanel = new JPanel(new GridLayout(2, 3, (int)( 0.025*getHeight() ), (int)( 0.005*getHeight() )));
        detailsPanel.setBackground(new Color(150, 200, 200));
        detailsPanel.setPreferredSize(new Dimension(getWidth(),(int) (getHeight() * 0.15)));

        generationNumberLabel = new JLabel("Generation #");
        generationNumberLabel.setHorizontalAlignment(JLabel.CENTER);
        generationNumberLabel.setFont(new Font(Font.SERIF,Font.BOLD,20));

        aliveNumberLabel = new JLabel("Alive: ");
        aliveNumberLabel.setHorizontalAlignment(JLabel.CENTER);
        aliveNumberLabel.setFont(new Font(Font.SERIF,Font.BOLD,20));

        JButton pauseButton = new JButton("START");
        pauseButton.setFont(new Font(Font.SERIF,Font.BOLD,20));
        pauseButton.addActionListener(e -> {
            if (paused == true) {
                paused = false;
                pauseButton.setText("PAUSE");
            } else {
                paused = true;
                pauseButton.setText("START");
            }
        });

        JButton restartButton = new JButton("RESTART");
        restartButton.setFont(new Font(Font.SERIF,Font.BOLD,20));
        restartButton.addActionListener(e -> restartRequested = true);

        JSlider speedSlider = new JSlider(JSlider.HORIZONTAL,0,100, requestedSpeed);
        speedSlider.addChangeListener(e -> requestedSpeed = speedSlider.getValue());
        speedSlider.setMajorTickSpacing(25);
        speedSlider.setMinorTickSpacing(5);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);
        speedSlider.setOpaque(false);

        JSlider sizeSlider = new JSlider(JSlider.HORIZONTAL,10,300, requestedSize);
        sizeSlider.addChangeListener(e -> requestedSize = sizeSlider.getValue());
        Hashtable labelTable = new Hashtable();
        labelTable.put(Integer.valueOf(10), new JLabel("10") );
        labelTable.putAll(sizeSlider.createStandardLabels(100,100));
        sizeSlider.setLabelTable( labelTable );
        sizeSlider.setMinorTickSpacing(10);
        sizeSlider.setPaintTicks(true);
        sizeSlider.setPaintLabels(true);
        sizeSlider.setOpaque(false);

        detailsPanel.add(generationNumberLabel);
        detailsPanel.add(speedSlider);
        detailsPanel.add(pauseButton);
        detailsPanel.add(aliveNumberLabel);
        detailsPanel.add(sizeSlider);
        detailsPanel.add(restartButton);

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
        universePanel.calculateSizeOfCells();
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

    public boolean isRestartRequested(){
        return restartRequested;
    }

    public void setRestartRequested(boolean restartRequested) {
        this.restartRequested = restartRequested;
    }
    public int getRequestedSpeed() {
        return requestedSpeed;
    }
    public int getRequestedSize() {
        return requestedSize;
    }
//    public void calculateSizeOfCells() {
//        universePanel.calculateSizeOfCells();
//    }
}
