package se.jtiden.ml.core.impl;

import se.jtiden.ml.core.api.AlgorithmStepPainter;
import se.jtiden.ml.core.api.HypothesisPainterFactory;
import se.jtiden.ml.core.api.IterativeAlgorithm;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;

public class MonaLisaAlgorithmPainter implements AlgorithmStepPainter {
    private IterativeAlgorithm algorithm;
    private HypothesisPainterFactory hypothesisPainterFactory;


    public MonaLisaAlgorithmPainter(
            IterativeAlgorithm algorithm,
            final HypothesisPainterFactory hypothesisPainterFactory) {
        this.algorithm = algorithm;
        this.hypothesisPainterFactory = hypothesisPainterFactory;
    }


    @Override
    public void paint() {
        throw new NotImplementedException();
    }

    @Override
    public Image getImage() {
        throw new NotImplementedException();
    }

    @Override
    public int getWidth() {
        return hypothesisPainterFactory.getWidth();
    }

    @Override
    public int getHeight() {
        return hypothesisPainterFactory.getHeight();
    }
}
