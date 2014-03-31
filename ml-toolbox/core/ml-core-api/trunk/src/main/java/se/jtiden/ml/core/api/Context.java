package se.jtiden.ml.core.api;


public interface Context {
    IterativeAlgorithm getAlgorithm();
    HypothesisPainterFactory getHypothesisPainterFactory();

    int getScale();
}
