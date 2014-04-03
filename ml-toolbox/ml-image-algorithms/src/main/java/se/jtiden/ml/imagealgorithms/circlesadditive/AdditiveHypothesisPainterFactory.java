package se.jtiden.ml.imagealgorithms.circlesadditive;


import se.jtiden.ml.imagealgorithms.painter.AlgorithmStepPainter;
import se.jtiden.ml.imagealgorithms.painter.HypothesisPainterFactory;

public class AdditiveHypothesisPainterFactory implements HypothesisPainterFactory<AdditiveHypothesis> {
    private int width;
    private int height;

    public AdditiveHypothesisPainterFactory(final int width, final int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public AlgorithmStepPainter create(final AdditiveHypothesis hypothesis) {
        return new AdditiveHypothesisPainter(hypothesis, width, height);
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
