package se.jtiden.ml.core.impl.nearestneighbor;

import se.jtiden.ml.core.api.AlgorithmStepPainter;
import se.jtiden.ml.core.api.HypothesisPainterFactory;

public class MonaLisaNearestNeighborHypothesisPainterFactory implements HypothesisPainterFactory<MonaLisaNearestNeighborHypothesis> {
    private final int alpha;
    private final int fakePixelSize;

    public MonaLisaNearestNeighborHypothesisPainterFactory(final int alpha, final int fakePixelSize) {

        this.alpha = alpha;
        this.fakePixelSize = fakePixelSize;
    }

    @Override
    public AlgorithmStepPainter create(final MonaLisaNearestNeighborHypothesis hypothesis) {
        return new MonaLisaNearestNeighborHypothesisPainter(hypothesis, null, alpha, fakePixelSize);
    }
}
