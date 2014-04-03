package se.jtiden.ml.core;

import se.jtiden.ml.core.algorithm.api.IterativeAlgorithm;
import se.jtiden.ml.core.painter.AlgorithmStepPainter;
import se.jtiden.ml.core.painter.HypothesisPainterFactory;
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
