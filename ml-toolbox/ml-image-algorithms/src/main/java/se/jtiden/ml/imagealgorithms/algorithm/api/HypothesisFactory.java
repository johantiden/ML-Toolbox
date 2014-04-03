package se.jtiden.ml.imagealgorithms.algorithm.api;

public interface HypothesisFactory<T extends Hypothesis> {

    T create();

}
