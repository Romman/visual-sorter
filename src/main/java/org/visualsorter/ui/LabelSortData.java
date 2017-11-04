package org.visualsorter.ui;

import org.visualsorter.api.SortData;

import javax.swing.*;

class LabelSortData implements SortData {

    private LabelDataSource dataSource;
    private Statistics statistics;

    public LabelSortData(LabelDataSource dataSource, Statistics statistics) {
        this.dataSource = dataSource;
        this.statistics = statistics;
    }

    @Override
    public int size() {
        return dataSource.getDataSize();
    }

    @Override
    public int getValue(int index) {
        statistics.access();
        dataSource.getLabel(index); // Visual update of the cell
        return dataSource.getHeight(index);
    }

    @Override
    public void setValue(int index, int value) {
        statistics.mutate();
        JLabel label = dataSource.getLabel(index);
        dataSource.setLabelHeight(label, value);
        dataSource.setHeight(index, value);
    }
}
