package se.jtiden.ml.imagealgorithms.painter;

import com.sun.istack.internal.Nullable;
import se.jtiden.ml.imagealgorithms.algorithm.api.Hypothesis;

import java.io.Serializable;

public interface HypothesisPainterFactory<T extends Hypothesis> extends Serializable {
    AlgorithmStepPainter create(@Nullable final T hypothesis);

    int getWidth();

    int getHeight();
}
