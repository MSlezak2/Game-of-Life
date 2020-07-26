package life.view;

import javax.swing.*;
import java.awt.*;

public class GameOfLife extends JFrame {

    private JPanel detailsPanel;
    public UniverseViewPanel universePanel;
    private JLabel currentGenerationLabel;
    private JLabel aliveNumberLabel;

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
        setSize(width,height);
        setVisible(true);


    }

    private void setupDetailsPanel() {

        detailsPanel = new JPanel(new GridLayout(2, 1));
        detailsPanel.setBackground(new Color(150, 200, 200));
        detailsPanel.setPreferredSize(new Dimension(this.getWidth(),(int) (this.getHeight() * 0.1)));

        currentGenerationLabel = new JLabel("Generation #");
        detailsPanel.add(currentGenerationLabel);

        aliveNumberLabel = new JLabel("Alive: ");
        detailsPanel.add(aliveNumberLabel);

        add(detailsPanel);

    }

    private void setupUniversePanel() {

        universePanel = new UniverseViewPanel();
        universePanel.setBackground(new Color(150,175,150));
        universePanel.setPreferredSize(new Dimension(this.getWidth(),(int) (this.getHeight() * 0.9)));

        add(universePanel);

    }

}
