package se.jtiden.ml.imagealgorithms.algorithm.api;

import java.io.Serializable;

public interface Hypothesis extends Comparable<Hypothesis>, Serializable {
    double valueFunction();

    Hypothesis copy();
}
