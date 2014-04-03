package se.jtiden.ml.core;

import se.jtiden.ml.core.algorithm.api.IterativeAlgorithm;
import se.jtiden.ml.core.painter.AlgorithmStepPainter;
import se.jtiden.ml.core.painter.HypothesisPainterFactory;

public class ContextImpl implements Context {

    private IterativeAlgorithm algorithm;
    private HypothesisPainterFactory hypothesisPainterFactory;
    private double scale;

    public ContextImpl(IterativeAlgorithm algorithm, AlgorithmStepPainter painter, HypothesisPainterFactory hypothesisPainterFactory, final double scale) {
        this.algorithm = algorithm;
        this.hypothesisPainterFactory = hypothesisPainterFactory;
        this.scale = scale;
    }

    @Override
    public IterativeAlgorithm getAlgorithm() {
        return algorithm;
    }

    @Override
    public HypothesisPainterFactory getHypothesisPainterFactory() {
        return hypothesisPainterFactory;
    }

    @Override
    public double getScale() {
        return scale;
    }
}
