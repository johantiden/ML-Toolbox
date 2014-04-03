package se.jtiden.ml.core;


import se.jtiden.ml.core.algorithm.api.IterativeAlgorithm;
import se.jtiden.ml.core.painter.HypothesisPainterFactory;

public interface Context {
    IterativeAlgorithm getAlgorithm();
    HypothesisPainterFactory getHypothesisPainterFactory();

    double getScale();
}
