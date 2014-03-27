package se.jtiden.ml.core.api;

public interface HypothesisPainterFactory<T extends Hypothesis> {
    AlgorithmStepPainter create(final T hypothesis);
}
