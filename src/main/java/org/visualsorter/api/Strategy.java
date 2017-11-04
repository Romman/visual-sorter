package org.visualsorter.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates the SorterStrategy implementation.
 *
 * A class annotated with {code}Strategy{code} annotation
 * and implemented {@link SorterStrategy} interface is considered
 * as a registered implementation of a sorting algorithm.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Strategy {

    /**
     * Displayed name of the strategy.
     */
    String value();

    /**
     * Defines the strategy position in the strategy list.
     * A strategy with the order equaled 0 is positioning as the first one.
     */
    int order() default 0;
}
