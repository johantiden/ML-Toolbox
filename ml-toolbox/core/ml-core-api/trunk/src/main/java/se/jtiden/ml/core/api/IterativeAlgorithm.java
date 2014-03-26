package se.jtiden.ml.core.api;

public interface IterativeAlgorithm<T extends Hypothesis> {
    void iterate();
}