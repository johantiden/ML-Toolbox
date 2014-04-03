package se.jtiden.ml.imagealgorithms.algorithm.api;

public interface Hypothesis extends Comparable<Hypothesis> {
    double valueFunction();

    Hypothesis copy();
}
