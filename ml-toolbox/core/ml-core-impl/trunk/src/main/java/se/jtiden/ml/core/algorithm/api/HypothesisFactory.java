package se.jtiden.ml.core.algorithm.api;

public interface HypothesisFactory<T extends Hypothesis> {

    T create();

}
