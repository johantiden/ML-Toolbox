package se.jtiden.ml.core.painter;

import se.jtiden.ml.core.algorithm.api.Hypothesis;
import se.jtiden.ml.core.painter.AlgorithmStepPainter;

public interface HypothesisPainterFactory<T extends Hypothesis> {
    AlgorithmStepPainter create(final T hypothesis);

    int getWidth();
    int getHeight();
}
