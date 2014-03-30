package se.jtiden.ml.core.impl;

import se.jtiden.ml.core.api.AlgorithmStepPainter;
import se.jtiden.ml.core.api.Context;
import se.jtiden.ml.core.api.HypothesisPainterFactory;
import se.jtiden.ml.core.api.IterativeAlgorithm;

public class ContextImpl implements Context {

    private IterativeAlgorithm algorithm;
    private HypothesisPainterFactory hypothesisPainterFactory;

    public ContextImpl(IterativeAlgorithm algorithm, AlgorithmStepPainter painter, HypothesisPainterFactory hypothesisPainterFactory) {
        this.algorithm = algorithm;
        this.hypothesisPainterFactory = hypothesisPainterFactory;
    }

    @Override
    public IterativeAlgorithm getAlgorithm() {
        return algorithm;
    }

    @Override
    public HypothesisPainterFactory getHypothesisPainterFactory() {
        return hypothesisPainterFactory;
    }
}
