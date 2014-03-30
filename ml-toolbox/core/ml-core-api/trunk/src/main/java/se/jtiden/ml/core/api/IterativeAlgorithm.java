package se.jtiden.ml.core.api;

public interface IterativeAlgorithm<T extends Hypothesis, U> {
    void iterate();
    T getBestHypothesis();
    Evaluator<U> getEvaluator();
}