package se.jtiden.ml.core.algorithm.api;

import se.jtiden.ml.core.evaluator.Evaluator;

public interface IterativeAlgorithm<T extends Hypothesis, U> {
    void iterate();
    T getBestHypothesis();
    Evaluator<U> getEvaluator();
}