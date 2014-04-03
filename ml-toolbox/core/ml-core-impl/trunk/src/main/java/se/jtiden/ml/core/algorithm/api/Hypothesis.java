package se.jtiden.ml.core.algorithm.api;

public interface Hypothesis extends Comparable<Hypothesis> {
    double valueFunction();
}
