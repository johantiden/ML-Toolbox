package se.jtiden.ml.imagealgorithms;

import se.jtiden.ml.imagealgorithms.algorithm.api.IterativeAlgorithm;
import se.jtiden.ml.imagealgorithms.painter.HypothesisPainterFactory;

public interface Context {
    IterativeAlgorithm getAlgorithm();

    HypothesisPainterFactory getHypothesisPainterFactory();

    double getScale();
}
