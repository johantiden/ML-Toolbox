package se.jtiden.ml.core.circlesadditive;

import se.jtiden.ml.core.painter.AlgorithmStepPainter;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;

public class AdditiveHypothesisPainter implements AlgorithmStepPainter {

    private final AdditiveHypothesis hypothesis;
    private int width;
    private int height;

    public AdditiveHypothesisPainter(AdditiveHypothesis hypothesis,
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
    public Image getImage() {
        return hypothesis.getImage().asImage();
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
