package se.jtiden.ml.core.impl.nearestneighbor;

import se.jtiden.ml.core.api.AlgorithmStepPainter;
import se.jtiden.ml.core.api.HypothesisPainterFactory;

public class MonaLisaNearestNeighborHypothesisPainterFactory implements HypothesisPainterFactory<MonaLisaNearestNeighborHypothesis> {
    private int width;
    private int height;

    public MonaLisaNearestNeighborHypothesisPainterFactory(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public AlgorithmStepPainter create(final MonaLisaNearestNeighborHypothesis hypothesis) {
        return new MonaLisaNearestNeighborHypothesisPainter(hypothesis);
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }
}
