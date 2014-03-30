package se.jtiden.ml.core.api;

public interface Hypothesis extends Comparable<Hypothesis> {
    double valueFunction();
}
