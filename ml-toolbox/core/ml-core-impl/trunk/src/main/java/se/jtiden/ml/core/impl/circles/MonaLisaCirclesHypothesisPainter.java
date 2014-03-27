package se.jtiden.ml.core.impl.circles;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

import se.jtiden.ml.core.api.AlgorithmStepPainter;
import se.jtiden.ml.core.api.CircleWithColor;
import se.jtiden.ml.core.api.Point;
import se.jtiden.ml.core.impl.AbstractAlgorithmPainter;

public class MonaLisaCirclesHypothesisPainter extends AbstractAlgorithmPainter {

    private final MonaLisaCirclesHypothesis hypothesis;
    private final int alpha;
    private final Map<Point, Color> colorCache = new TreeMap<Point, Color>();
    private int fakePixelSize;
    private BufferedImage cachedImage;
    private int width;
    private int height;

    public MonaLisaCirclesHypothesisPainter(MonaLisaCirclesHypothesis hypothesis,
                                            AlgorithmStepPainter innerPainter,
                                            int alpha,
                                            int fakePixelSize, final int width, final int height) {
        super(innerPainter);
        this.hypothesis = hypothesis;
        this.alpha = alpha;
        this.fakePixelSize = fakePixelSize;
        this.width = width;
        this.height = height;
    }


    public Color colorAt(final int x, final int y) {
        if (cachedImage == null) {
            paintCachedImage();
        }

        int argb = cachedImage.getRGB(x, y);

        return new Color(
                (argb >> 16) & 0xff, //red
                (argb >> 8) & 0xff, //green
                (argb) & 0xff  //blue
        );
    }


    private void paintCachedImage() {
        cachedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);

        Graphics g = cachedImage.getGraphics();
        for (CircleWithColor circle : hypothesis.getCircles()) {
            paintCircle(g, circle);
        }
    }

    private void paintCircle(final Graphics g, final CircleWithColor circle) {
        g.setColor(circle.color);
        g.fillOval(circle.left(), circle.top(), circle.diameter(), circle.diameter());
    }

    @Override
    public void paint(Graphics g) {

        if (innerPainter != null) {
            innerPainter.paint(g);
        }

        for (int y = 0; y < hypothesis.getMonaLisa().getHeight(); y += fakePixelSize) {
            for (int x = 0; x < hypothesis.getMonaLisa().getWidth(); x += fakePixelSize) {
                paintPixel(g, colorAt(x, y), x, y);
            }
        }
    }

    private void paintPixel(Graphics g, Color color, int x, int y) {
        g.setColor(color);
        g.fillRect(x, y, fakePixelSize, fakePixelSize);
    }

    @Override
    public int getWidth() {
        return innerPainter != null ? Math.max(innerPainter.getWidth(), width) : width;
    }

    @Override
    public int getHeight() {
        return innerPainter != null ? Math.max(innerPainter.getHeight(), height) : height;
    }
}
