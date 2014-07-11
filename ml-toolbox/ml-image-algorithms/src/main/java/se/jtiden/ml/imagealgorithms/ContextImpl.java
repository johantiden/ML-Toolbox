package se.jtiden.ml.imagealgorithms;

import se.jtiden.ml.imagealgorithms.algorithm.api.IterativeAlgorithm;
import se.jtiden.ml.imagealgorithms.painter.HypothesisPainterFactory;

public class ContextImpl implements Context {

    private IterativeAlgorithm algorithm;
    private HypothesisPainterFactory hypothesisPainterFactory;
    private double scale;
    private double paintFPS;

    public ContextImpl(IterativeAlgorithm algorithm, HypothesisPainterFactory hypothesisPainterFactory, final double scale, final double paintFPS) {
        this.algorithm = algorithm;
        this.hypothesisPainterFactory = hypothesisPainterFactory;
        this.scale = scale;
        this.paintFPS = paintFPS;
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

    @Override
    public double getPaintFPS() {
        return paintFPS;
    }
}
