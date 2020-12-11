package life.view;

import life.model.Universe;

import javax.swing.*;
import java.awt.*;


public class GameOfLife extends JFrame {

    private DetailsPanel detailsPanel;
    private UniverseViewPanel universePanel;

    private int minWidth;
    private int minHeight;

    private boolean pauseRequested = true; // initial states of particular controls
    private boolean restartRequested = true;
    private int requestedSpeed = 50;
    private int requestedSize = 50;

    static final Color TOP_PANEL_BCK = new Color(0x80,0xd8,0xff); // color of particular GUI components
    static final Color BOTTOM_PANEL_BCK = new Color(0xff,0xff,0xff);
    static final Color BUTTONS_BCK = new Color(0xe3,0xf2,0xfd);
    static final Color FONT = new Color(0x00,0x00,0x00);
    static final Color ALIVE_PIXELS = new Color(0x49,0xa7,0xcc);
    static final Color GRID = new Color(0xb1,0xbf,0xca);


    public GameOfLife(int windowWidth, int windowHeight) {

        setupFrame(windowWidth, windowHeight);
        setupDetailsPanel();
        setupUniversePanel();

    }

    private void setupFrame(int width, int height) {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        setSize(width,height);
        setVisible(true);

    }

    private void setupDetailsPanel() {

        detailsPanel = new DetailsPanel(this);
        detailsPanel.setPreferredSize(new Dimension(getWidth(),(int) (getHeight() * 0.15)));

        add(detailsPanel);

    }


    private void setupUniversePanel() {

        universePanel = new UniverseViewPanel();
        universePanel.setPreferredSize(new Dimension(getWidth(),(int) (getHeight() * 0.9)));

        add(universePanel);

    }


    public void drawUniverse(Universe universe) {

        // updating statistics of current iteration
        detailsPanel.getGenerationNumberLabel().setText("Generation #" + universe.getGenerationNumber());
        detailsPanel.getAliveNumberLabel().setText("Alive: " + universe.getAliveNumber());

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

    public boolean isPauseRequested(){
        return pauseRequested;
    }
    public boolean isRestartRequested(){
        return restartRequested;
    }
    public int getRequestedSpeed() {
        return requestedSpeed;
    }
    public int getRequestedSize() {
        return requestedSize;
    }

    public void setPauseRequested(boolean pauseRequested) {
        this.pauseRequested = pauseRequested;
    }
    public void setRequestedSpeed(int requestedSpeed) {
        this.requestedSpeed = requestedSpeed;
    }
    public void setRequestedSize(int requestedSize) {
        this.requestedSize = requestedSize;
    }
    public void setRestartRequested(boolean restartRequested) {
        this.restartRequested = restartRequested;
    }

}
