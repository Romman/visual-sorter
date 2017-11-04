package org.visualsorter.ui;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Represents a container ({@link JPanel}) that operates sortable data (labels).
 */
public class LabelDataPanel extends JPanel implements LabelDataSource {

    private static final int INITIAL_SIZE = 40;
    private static final Dimension MINIMUM_SIZE = new Dimension(200, 200);

    private SorterFrame parentFrame;

    private JLabel[] labels;
    private int[] heights;
    private int dataSize;

    private boolean showElementValue = true;
    private int elementWidth;
    private int lastAccessedIndex;

    LabelDataPanel(SorterFrame frame) {
        super(new FlowLayout(FlowLayout.CENTER, 0, 0));
        this.parentFrame = frame;
        setAlignmentY(Component.BOTTOM_ALIGNMENT);
        setDataSize(INITIAL_SIZE);
        setMinimumSize(MINIMUM_SIZE);
        setPreferredSize(MINIMUM_SIZE);
    }

    void rebuild() {
        removeAll();

        Random random = new Random(System.currentTimeMillis());

        this.labels = new JLabel[this.dataSize];
        this.heights = new int[this.dataSize];

        for (int i = 0; i < this.dataSize; ++i) {
            int height = random.nextInt(195) + 5;
            this.labels[i] = createLabel(height);
            this.heights[i] = height;
            add(this.labels[i]);
        }

        this.lastAccessedIndex = 0;
    }

    void setDataSize(int size) {
        this.dataSize = size;

        this.elementWidth = (SorterFrame.FRAME_WIDTH - 50) / this.dataSize;
        this.showElementValue = this.dataSize <= 40;
    }

    @Override
    public int getDataSize() {
        return this.dataSize;
    }

    void updateVisual() {
        // Repaint the panel
        revalidate();
        repaint();
        // Call to repaint immediately
        paintImmediately(0, 0, SorterFrame.FRAME_WIDTH, SorterFrame.FRAME_HEIGHT);
    }

    private JLabel createLabel(int height) {
        JLabel label = new JLabel();
        setLabelHeight(label, height);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBackground(Color.GRAY);
        return label;
    }

    @Override
    public void setLabelHeight(JLabel label, int height) {
        label.setPreferredSize(new Dimension(this.elementWidth, height));
        if (this.showElementValue) {
            label.setText(Integer.toString(height / 2));
        }
    }

    @Override
    public JLabel getLabel(int index) {
        this.labels[lastAccessedIndex].setBackground(Color.GRAY);
        this.labels[index].setBackground(Color.GREEN);
        lastAccessedIndex = index;
        parentFrame.repaint();
        return this.labels[index];
    }

    @Override
    public int getHeight(int index) {
        return this.heights[index];
    }

    @Override
    public void setHeight(int index, int newValue) {
        this.heights[index] = newValue;
    }
}
