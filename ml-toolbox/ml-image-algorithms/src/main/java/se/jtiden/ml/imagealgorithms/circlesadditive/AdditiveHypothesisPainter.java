package se.jtiden.ml.imagealgorithms.circlesadditive;

import com.sun.istack.internal.NotNull;
import se.jtiden.common.images.JTImage;
import se.jtiden.ml.imagealgorithms.painter.AlgorithmStepPainter;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class AdditiveHypothesisPainter implements AlgorithmStepPainter {

    private final AdditiveHypothesis hypothesis;
    private int width;
    private int height;

    public AdditiveHypothesisPainter(@NotNull AdditiveHypothesis hypothesis,
                                     final int width, final int height) {
        this.hypothesis = hypothesis;
        this.width = width;
        this.height = height;
    }


    @Override
    public void paint() {
        throw new NotImplementedException();
    }

    @Override
    public JTImage getImage() {
        return hypothesis.getImage();
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
