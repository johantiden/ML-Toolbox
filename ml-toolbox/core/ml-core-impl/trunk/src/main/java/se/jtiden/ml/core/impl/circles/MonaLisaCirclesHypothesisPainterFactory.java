package se.jtiden.ml.core.impl.circles;

import se.jtiden.ml.core.api.AlgorithmStepPainter;
import se.jtiden.ml.core.api.HypothesisPainterFactory;

public class MonaLisaCirclesHypothesisPainterFactory implements HypothesisPainterFactory<MonaLisaCirclesHypothesis> {
    private final int alpha;
    private final int fakePixelSize;
    private int width;
    private int height;

    public MonaLisaCirclesHypothesisPainterFactory(final int alpha, final int fakePixelSize, final int width, final int height) {
        this.alpha = alpha;
        this.fakePixelSize = fakePixelSize;
        this.width = width;
        this.height = height;
    }

    @Override
    public AlgorithmStepPainter create(final MonaLisaCirclesHypothesis hypothesis) {
        return new MonaLisaCirclesHypothesisPainter(hypothesis, null, alpha, fakePixelSize, width, height);
    }
}
