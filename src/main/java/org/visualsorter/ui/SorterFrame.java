package org.visualsorter.ui;

import org.visualsorter.StrategyImplementation;
import org.visualsorter.api.SortData;
import org.visualsorter.api.SorterStrategy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Application main frame.
 */
public class SorterFrame {

    public static final int FRAME_WIDTH = 850;
    public static final int FRAME_HEIGHT = 500;

    private static class SortActionListener implements ActionListener {

        private SortData sortData;
        private SorterStrategy strategy;

        SortActionListener(SortData sortData, SorterStrategy strategy) {
            this.sortData = sortData;
            this.strategy = strategy;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            strategy.sort(sortData);
        }
    }

    private JFrame frame;
    private LabelDataPanel labelDataPanel;
    private StatisticsPanel statisticsPanel;
    private List<StrategyImplementation> strategyImplementations;

    /**
     * Available choice options to select the sortable data amount
     */
    private List<Integer> dataSizes;

    public SorterFrame() {
        this.frame = new JFrame("Visual Sorter");
        this.frame.setLayout(new BorderLayout());
        this.frame.setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void setStrategies(List<StrategyImplementation> strategyImplementations) {
        this.strategyImplementations = strategyImplementations;
    }

    /**
     * Creates and initializes all frame UI-components.
     */
    public void init() {
        // Initialize sizes choice according to the configuration.
        ResourceBundle configuration = ResourceBundle.getBundle("configuration");

        String[] stringDataSizes = configuration.getString("sizes").split(",");
        this.dataSizes = Arrays.stream(stringDataSizes)
                .map(String::trim)
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        Integer initialSize = this.dataSizes.get(0);
        if (configuration.containsKey("initialSize")) {
            initialSize = Integer.valueOf(configuration.getString("initialSize"));
        }

        this.labelDataPanel = new LabelDataPanel(this);
        this.labelDataPanel.setDataSize(initialSize);
        this.labelDataPanel.rebuild();
        this.statisticsPanel = new StatisticsPanel();

        //
        // Element count ComboBox
        //
        final JComboBox<String> elementComboBox = new JComboBox<>(stringDataSizes);
        elementComboBox.setSelectedIndex(this.dataSizes.indexOf(initialSize));
        elementComboBox.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int newSize = SorterFrame.this.dataSizes.get(elementComboBox.getSelectedIndex());
                SorterFrame.this.labelDataPanel.setDataSize(newSize);
                SorterFrame.this.shuffle();
            }
        });

        JPanel elementsCountPanel = new JPanel(new FlowLayout());
        elementsCountPanel.add(elementComboBox);

        //
        // Sort Control Buttons
        //
        JPanel buttonPanel =  new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        SortData sortData = new LabelSortData(this.labelDataPanel, this.statisticsPanel);

        for (StrategyImplementation strategy : this.strategyImplementations) {
            JButton button = new JButton(strategy.getName());
            button.addActionListener(new SortActionListener(sortData, strategy.getInstance()));
            buttonPanel.add(button);
        }

        JButton button = new JButton("Shuffle");
        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shuffle();
            }
        });
        buttonPanel.add(button);

        //
        // Frame Layout
        //
        JPanel controlPanel = new JPanel(new BorderLayout());
        controlPanel.add(elementsCountPanel, BorderLayout.LINE_START);
        controlPanel.add(buttonPanel, BorderLayout.LINE_END);

        this.frame.add(this.labelDataPanel, BorderLayout.PAGE_START);
        this.frame.add(this.statisticsPanel, BorderLayout.CENTER);
        this.frame.add(controlPanel, BorderLayout.PAGE_END);
    }

    public void show() {
        this.frame.setVisible(true);
    }

    private void shuffle() {
        this.statisticsPanel.reset();
        this.labelDataPanel.rebuild();
        repaint();
    }

    void repaint() {
        this.statisticsPanel.updateVisual();
        this.labelDataPanel.updateVisual();
        // Repaint the whole window
        frame.revalidate();
        frame.repaint();
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
