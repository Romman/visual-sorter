package org.visualsorter.ui;

import javax.swing.*;

public interface LabelDataSource {

    int getDataSize();

    JLabel getLabel(int index);

    int getHeight(int index);

    void setLabelHeight(JLabel label, int height);

    void setHeight(int index, int newValue);
}
