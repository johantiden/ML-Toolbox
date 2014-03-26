package se.jtiden.ml.core.impl;

import se.jtiden.ml.core.api.AlgorithmStepPainter;
import se.jtiden.ml.core.api.Context;
import se.jtiden.ml.core.api.IterativeAlgorithm;

public class ContextImpl implements Context {


    private IterativeAlgorithm algorithm;
    private AlgorithmStepPainter painter;

    public ContextImpl(IterativeAlgorithm algorithm, AlgorithmStepPainter painter) {
        this.algorithm = algorithm;
        this.painter = painter;
    }


    @Override
    public AlgorithmStepPainter getPainter() {
        return painter;
    }

    @Override
    public IterativeAlgorithm getAlgorithm() {
        return algorithm;
    }
}
