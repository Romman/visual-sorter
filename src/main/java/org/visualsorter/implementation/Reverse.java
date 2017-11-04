package org.visualsorter.implementation;

import org.visualsorter.api.SortData;
import org.visualsorter.api.SorterStrategy;
import org.visualsorter.api.Strategy;

/**
 * This is a demo implementation of a sorting strategy
 * that just simply reverses the order of elements in the @{@link SortData} object.
 *
 * Uncomment the @{@link Strategy} annotation to enable
 * this strategy as a selectable option of provided strategies.
 */
@Strategy("Reverse")
public class Reverse implements SorterStrategy {

    @Override
    public void sort(SortData data) {
        int i = 0;
        while (i < data.size() / 2) {
            int left = data.getValue(i);
            int right = data.getValue(data.size() - 1 - i);

            data.setValue(i, right);
            data.setValue(data.size() - 1 - i, left);
            ++i;
        }
    }
}
