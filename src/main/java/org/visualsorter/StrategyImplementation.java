package org.visualsorter;

import org.visualsorter.api.SorterStrategy;

public class StrategyImplementation {

    private String name;
    private SorterStrategy instance;

    public StrategyImplementation(String name, SorterStrategy instance) {
        this.name = name;
        this.instance = instance;
    }

    public String getName() {
        return name;
    }

    public SorterStrategy getInstance() {
        return instance;
    }
}
