package se.jtiden.ml.core.api;

public interface HypothesisFactory<T extends Hypothesis> {

    T create();

}
