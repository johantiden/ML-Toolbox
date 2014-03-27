package se.jtiden.ml.core.impl;

import se.jtiden.ml.core.api.AlgorithmStepPainter;
import se.jtiden.ml.core.api.HypothesisPainterFactory;
import se.jtiden.ml.core.api.IterativeAlgorithm;

import java.awt.*;

public class MonaLisaAlgorithmPainter extends AbstractAlgorithmPainter {
    private IterativeAlgorithm algorithm;
    private HypothesisPainterFactory hypothesisPainterFactory;


    public MonaLisaAlgorithmPainter(
            IterativeAlgorithm algorithm,
            AlgorithmStepPainter innerPainter,
            final HypothesisPainterFactory hypothesisPainterFactory) {
        super(innerPainter);
        this.algorithm = algorithm;
        this.hypothesisPainterFactory = hypothesisPainterFactory;
    }

    @Override
    public void paint(Graphics g) {
        if (innerPainter != null) {
            innerPainter.paint(g);
        }

        hypothesisPainterFactory.create(algorithm.getBestHypothesis()).paint(g);
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
