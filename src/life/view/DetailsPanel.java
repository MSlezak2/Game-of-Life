package life.view;

import javax.swing.*;
import java.awt.*;
import java.util.Hashtable;

class DetailsPanel extends JPanel {

    private JLabel generationNumberLabel;
    private JLabel aliveNumberLabel;
    private JButton restartButton;
    private JButton pauseButton;
    private JSlider speedSlider;
    private JSlider sizeSlider;

    public DetailsPanel(GameOfLife gol) {

        setLayout(new GridLayout(2, 3, (int) (0.025 * gol.getHeight()), (int) (0.005 * gol.getHeight())));
        setBackground(GameOfLife.TOP_PANEL_BCK);

        formatLabels();
        formatButtons(gol);
        formatSliders(gol);

        add(generationNumberLabel);
        add(speedSlider);
        add(pauseButton);
        add(aliveNumberLabel);
        add(sizeSlider);
        add(restartButton);

    }

    private void formatButtons(GameOfLife gol) {
        pauseButton = new JButton("START");
        pauseButton.setFont(new Font(Font.SERIF,Font.BOLD,20));
        pauseButton.addActionListener(e -> {
            if (gol.isPauseRequested() == true) {
                gol.setPauseRequested(false);
                pauseButton.setText("PAUSE");
            } else {
                gol.setPauseRequested(true);
                pauseButton.setText("START");
            }
        });
        pauseButton.setForeground(GameOfLife.FONT);
        pauseButton.setBackground(GameOfLife.BUTTONS_BCK);

        restartButton = new JButton("RESTART");
        restartButton.setFont(new Font(Font.SERIF,Font.BOLD,20));
        restartButton.addActionListener(e -> gol.setRestartRequested(true));
        restartButton.setForeground(GameOfLife.FONT);
        restartButton.setBackground(GameOfLife.BUTTONS_BCK);
    }

    private void formatLabels() {
        generationNumberLabel = new JLabel("Generation #");
        generationNumberLabel.setHorizontalAlignment(JLabel.CENTER);
        generationNumberLabel.setFont(new Font(Font.SERIF,Font.BOLD,20));
        generationNumberLabel.setForeground(GameOfLife.FONT);

        aliveNumberLabel = new JLabel("Alive: ");
        aliveNumberLabel.setHorizontalAlignment(JLabel.CENTER);
        aliveNumberLabel.setFont(new Font(Font.SERIF,Font.BOLD,20));
        aliveNumberLabel.setForeground(GameOfLife.FONT);
    }

    private void formatSliders(GameOfLife gol) {

        speedSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, gol.getRequestedSpeed());
        speedSlider.addChangeListener(e -> gol.setRequestedSpeed(speedSlider.getValue()));
        speedSlider.setMajorTickSpacing(25);
        speedSlider.setMinorTickSpacing(5);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);
        speedSlider.setOpaque(false);
        speedSlider.setForeground(GameOfLife.FONT);

        sizeSlider = new JSlider(JSlider.HORIZONTAL, 10, 300, gol.getRequestedSize());
        sizeSlider.addChangeListener(e -> gol.setRequestedSize(sizeSlider.getValue()));

        // customizing distribution of the labels
        Hashtable<Integer,JComponent> labelTable = new Hashtable<>();
        labelTable.put(Integer.valueOf(10), new JLabel("10"));
        labelTable.putAll(sizeSlider.createStandardLabels(100, 100));
        sizeSlider.setLabelTable(labelTable);

        sizeSlider.setMinorTickSpacing(10);
        sizeSlider.setPaintTicks(true);
        sizeSlider.setPaintLabels(true);
        sizeSlider.setOpaque(false);
        sizeSlider.setForeground(GameOfLife.FONT);

    }

    public JLabel getGenerationNumberLabel() {
        return generationNumberLabel;
    }

    public JLabel getAliveNumberLabel() {
        return aliveNumberLabel;
    }

}
