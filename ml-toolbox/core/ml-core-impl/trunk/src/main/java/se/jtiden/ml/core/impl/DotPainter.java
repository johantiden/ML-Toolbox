package se.jtiden.ml.core.impl;

import se.jtiden.ml.core.api.AlgorithmStepPainter;
import se.jtiden.ml.core.api.Point;

import java.awt.*;

public class DotPainter extends AbstractAlgorithmPainter {
    private final DotProducer dotProducer;
    private final int radius;

    public DotPainter(DotProducer dotProducer,
                      int radius,
                      AlgorithmStepPainter innerPainter) {
        super(innerPainter);
        this.dotProducer = dotProducer;
        this.radius = radius;
    }

    @Override
    public void paint(Graphics g) {

        if (innerPainter != null) {
            innerPainter.paint(g);
        }

        g.setColor(Color.RED);
        for (Point p : dotProducer.getPoints()) {
            g.fillOval(
                    p.xInt() - radius,
                    p.yInt() - radius,
                    radius*2,
                    radius*2);
        }
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
