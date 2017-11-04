package org.visualsorter.api;

public interface SorterStrategy {

    /**
     * Performs the sort operation for the specified data
     * @param data Initial data.
     */
    void sort(SortData data);
}
