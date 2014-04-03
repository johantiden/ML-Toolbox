package se.jtiden.ml.imagealgorithms.algorithm.api;

import se.jtiden.ml.imagealgorithms.evaluator.Evaluator;

public interface IterativeAlgorithm<T extends Hypothesis, U> {
    void iterate();
    T getBestHypothesis();
    Evaluator<U> getEvaluator();
}