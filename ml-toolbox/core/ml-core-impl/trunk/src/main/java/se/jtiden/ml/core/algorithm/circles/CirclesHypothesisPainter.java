package se.jtiden.ml.core.algorithm.circles;

import se.jtiden.common.images.CircleWithColor;
import se.jtiden.common.images.FastJTImage;
import se.jtiden.common.images.JTGraphics;
import se.jtiden.ml.core.painter.AlgorithmStepPainter;

import java.awt.*;

public class CirclesHypothesisPainter implements AlgorithmStepPainter {

    private final CirclesHypothesis hypothesis;
    private int width;
    private int height;

    public CirclesHypothesisPainter(CirclesHypothesis hypothesis,
                                    final int width, final int height) {
        this.hypothesis = hypothesis;
        this.width = width;
        this.height = height;
    }


    private void paintCircles(JTGraphics graphics) {
        for (CircleWithColor circle : hypothesis.getCircles()) {
            graphics.drawCircle(circle);
        }
    }

    @Override
    public void paint() {
        hypothesis.setCachedImage(new FastJTImage(getWidth(), getHeight()));
        JTGraphics graphics = hypothesis.getCachedImage().getGraphics();
        paintCircles(graphics);
    }

    @Override
    public Image getImage() {
        return hypothesis.getCachedImage().asImage();
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
