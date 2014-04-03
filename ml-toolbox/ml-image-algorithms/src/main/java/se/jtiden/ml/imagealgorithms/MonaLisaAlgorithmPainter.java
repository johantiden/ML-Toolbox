package se.jtiden.ml.imagealgorithms;

import java.awt.*;

import se.jtiden.ml.imagealgorithms.algorithm.api.IterativeAlgorithm;
import se.jtiden.ml.imagealgorithms.painter.AlgorithmStepPainter;
import se.jtiden.ml.imagealgorithms.painter.HypothesisPainterFactory;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
