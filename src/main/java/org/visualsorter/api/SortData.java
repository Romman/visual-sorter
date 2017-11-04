package org.visualsorter.api;

/**
 * Provides access to data, that are currently under sorting.
 */
public interface SortData {

    /**
     * Returns the amount of data
     * @return the number of sorting values
     */
    int size();

    /**
     * Gets a value at the specified position.
     * @param index Position to retrieve the value at
     * @return found value
     */
    int getValue(int index);

    /**
     * Sets the specified value to the specified position
     * @param index Position to set the value at.
     * @param value New value
     */
    void setValue(int index, int value);
}
