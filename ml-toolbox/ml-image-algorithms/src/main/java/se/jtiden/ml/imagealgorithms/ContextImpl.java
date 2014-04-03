package se.jtiden.ml.imagealgorithms;

import se.jtiden.ml.imagealgorithms.algorithm.api.IterativeAlgorithm;
import se.jtiden.ml.imagealgorithms.painter.AlgorithmStepPainter;
import se.jtiden.ml.imagealgorithms.painter.HypothesisPainterFactory;

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
