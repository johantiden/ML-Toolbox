package se.jtiden.ml.imagealgorithms.algorithm.api;

import se.jtiden.ml.imagealgorithms.evaluator.Evaluator;

import java.io.Serializable;

public interface IterativeAlgorithm<T extends Hypothesis, U> extends Serializable {
    void iterate();

    T getBestHypothesis();

    Evaluator<U> getEvaluator();
}