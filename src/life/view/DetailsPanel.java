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

    public DetailsPanel(GameOfLife got) {

        setLayout(new GridLayout(2, 3, (int) (0.025 * got.getHeight()), (int) (0.005 * got.getHeight())));
        setBackground(GameOfLife.TOP_PANEL_BCK);

        formatLabels();
        formatButtons(got);
        formatSliders(got);

        add(generationNumberLabel);
        add(speedSlider);
        add(pauseButton);
        add(aliveNumberLabel);
        add(sizeSlider);
        add(restartButton);

    }

    private void formatButtons(GameOfLife gof) {
        pauseButton = new JButton("START");
        pauseButton.setFont(new Font(Font.SERIF,Font.BOLD,20));
        pauseButton.addActionListener(e -> {
            if (gof.isPauseRequested() == true) {
                gof.setPauseRequested(false);
                pauseButton.setText("PAUSE");
            } else {
                gof.setPauseRequested(true);
                pauseButton.setText("START");
            }
        });
        pauseButton.setForeground(GameOfLife.FONT);
        pauseButton.setBackground(GameOfLife.BUTTONS_BCK);

        restartButton = new JButton("RESTART");
        restartButton.setFont(new Font(Font.SERIF,Font.BOLD,20));
        restartButton.addActionListener(e -> gof.setRestartRequested(true));
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

    private void formatSliders(GameOfLife gof) {

        speedSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, gof.getRequestedSpeed());
        speedSlider.addChangeListener(e -> gof.setRequestedSpeed(speedSlider.getValue()));
        speedSlider.setMajorTickSpacing(25);
        speedSlider.setMinorTickSpacing(5);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);
        speedSlider.setOpaque(false);
        speedSlider.setForeground(GameOfLife.FONT);

        sizeSlider = new JSlider(JSlider.HORIZONTAL, 10, 300, gof.getRequestedSize());
        sizeSlider.addChangeListener(e -> gof.setRequestedSize(sizeSlider.getValue()));

        // customizing distribution of the labels
        Hashtable labelTable = new Hashtable();
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
