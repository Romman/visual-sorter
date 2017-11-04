package org.visualsorter;

import org.reflections.Reflections;
import org.visualsorter.api.SorterStrategy;
import org.visualsorter.api.Strategy;

import java.util.*;
import java.util.stream.Collectors;

public class StrategyFinder {

    private static Comparator<Class<?>> STRATEGY_ORDER_COMPARATOR = Comparator.comparingInt(t -> t.getAnnotation(Strategy.class).order());

    private String basePackage;

    public StrategyFinder(String basePackage) {
        this.basePackage = basePackage;
    }

    public List<StrategyImplementation> findAll() {
        Reflections reflections = new Reflections(this.basePackage);

        return reflections.getTypesAnnotatedWith(Strategy.class).stream()
                .sorted(STRATEGY_ORDER_COMPARATOR)
                .map(this::mapClassToImplementation)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private StrategyImplementation mapClassToImplementation(Class<?> strategyClass) {
        Strategy strategy = strategyClass.getAnnotation(Strategy.class);

        try {
            SorterStrategy instance = (SorterStrategy) strategyClass.newInstance();
            return new StrategyImplementation(strategy.value(), instance);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
