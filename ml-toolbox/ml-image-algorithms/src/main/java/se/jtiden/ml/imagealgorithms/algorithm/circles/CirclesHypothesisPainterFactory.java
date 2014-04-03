package se.jtiden.ml.imagealgorithms.algorithm.circles;


import se.jtiden.ml.imagealgorithms.painter.AlgorithmStepPainter;
import se.jtiden.ml.imagealgorithms.painter.HypothesisPainterFactory;

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
