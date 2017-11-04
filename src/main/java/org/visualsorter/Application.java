package org.visualsorter;

import org.visualsorter.ui.SorterFrame;

import java.util.List;

public class Application {

    public static void main(String[] args) {
        StrategyFinder strategyFinder = new StrategyFinder("org.visualsorter.implementation");
        List<StrategyImplementation> strategies = strategyFinder.findAll();

        SorterFrame frame = new SorterFrame();
        frame.setStrategies(strategies);
        frame.init();
        frame.show();
    }
}
