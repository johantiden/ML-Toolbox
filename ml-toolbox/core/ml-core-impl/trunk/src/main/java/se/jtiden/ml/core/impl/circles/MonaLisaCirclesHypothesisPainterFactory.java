package se.jtiden.ml.core.impl.circles;

import se.jtiden.ml.core.api.AlgorithmStepPainter;
import se.jtiden.ml.core.api.HypothesisPainterFactory;

public class MonaLisaCirclesHypothesisPainterFactory implements HypothesisPainterFactory<MonaLisaCirclesHypothesis> {
    private int width;
    private int height;

    public MonaLisaCirclesHypothesisPainterFactory(final int width, final int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public AlgorithmStepPainter create(final MonaLisaCirclesHypothesis hypothesis) {
        return new MonaLisaCirclesHypothesisPainter(hypothesis, width, height);
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
