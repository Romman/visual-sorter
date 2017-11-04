package org.visualsorter.ui;

import javax.swing.*;
import java.awt.*;

public class StatisticsPanel extends JPanel implements Statistics {

    private JLabel accessLabel;
    private JLabel mutationLabel;

    private int accesses;
    private int mutations;

    public StatisticsPanel() {
        super(new FlowLayout(FlowLayout.RIGHT, 20, 20));

        this.accessLabel = new JLabel();
        this.mutationLabel = new JLabel();
        add(this.accessLabel);
        add(this.mutationLabel);
        updateVisual();
    }

    public void updateVisual() {
        this.accessLabel.setText("Accesses: " + Integer.toString(this.accesses));
        this.mutationLabel.setText("Mutations: " + Integer.toString(this.mutations));
        revalidate();
        repaint();
        paintImmediately(0, 0, SorterFrame.FRAME_WIDTH, SorterFrame.FRAME_HEIGHT);
    }

    public void reset() {
        this.accesses = 0;
        this.mutations = 0;
        updateVisual();
    }

    @Override
    public void access() {
        this.accesses++;
    }

    @Override
    public void mutate() {
        this.mutations++;
    }

}
