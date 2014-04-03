package se.jtiden.ml.imagealgorithms.painter;

import se.jtiden.ml.imagealgorithms.algorithm.api.Hypothesis;

public interface HypothesisPainterFactory<T extends Hypothesis> {
    AlgorithmStepPainter create(final T hypothesis);

    int getWidth();

    int getHeight();
}
