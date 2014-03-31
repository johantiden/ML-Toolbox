package se.jtiden.ml.core.impl.circles;

import se.jtiden.ml.core.api.AlgorithmStepPainter;
import se.jtiden.ml.core.api.HypothesisPainterFactory;

public class CirclesHypothesisPainterFactory implements HypothesisPainterFactory<CirclesHypothesis> {
    private int width;
    private int height;

    public CirclesHypothesisPainterFactory(final int width, final int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public AlgorithmStepPainter create(final CirclesHypothesis hypothesis) {
        return new CirclesHypothesisPainter(hypothesis, width, height);
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
