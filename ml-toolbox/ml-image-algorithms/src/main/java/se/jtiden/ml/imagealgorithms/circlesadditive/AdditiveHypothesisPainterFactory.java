package se.jtiden.ml.imagealgorithms.circlesadditive;


import com.sun.istack.internal.NotNull;
import se.jtiden.ml.imagealgorithms.painter.AlgorithmStepPainter;
import se.jtiden.ml.imagealgorithms.painter.HypothesisPainterFactory;

public class AdditiveHypothesisPainterFactory implements HypothesisPainterFactory<AdditiveHypothesis> {
    private static final long serialVersionUID = -6342635069591182972L;
    private final int width;
    private final int height;

    public AdditiveHypothesisPainterFactory(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public AlgorithmStepPainter create(@NotNull AdditiveHypothesis hypothesis) {
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
