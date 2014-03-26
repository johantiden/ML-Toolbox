package se.jtiden.ml.core.impl;

import se.jtiden.ml.core.api.AlgorithmStepPainter;

import java.awt.*;

public class MonaLisaAlgorithmPainter extends AbstractAlgorithmPainter {
    private MonaLisaNearestNeighborAlgorithm algorithm;
    private int alpha;
    private int fakePixelSize;


    public MonaLisaAlgorithmPainter(
            MonaLisaNearestNeighborAlgorithm algorithm,
            int alpha,
            AlgorithmStepPainter innerPainter, int fakePixelSize) {
        super(innerPainter);
        this.algorithm = algorithm;
        this.alpha = alpha;
        this.fakePixelSize = fakePixelSize;
    }

    @Override
    public void paint(Graphics g) {
        if (innerPainter != null) {
            innerPainter.paint(g);
        }

        new MonaLisaHypothesisPainter(
                algorithm.getBestHypothesis(),
                null,
                alpha,
                fakePixelSize).paint(g);
    }

    @Override
    public int getWidth() {
        return innerPainter != null ? innerPainter.getWidth() : 0;
    }

    @Override
    public int getHeight() {
        return innerPainter != null ? innerPainter.getHeight() : 0;
    }
}
