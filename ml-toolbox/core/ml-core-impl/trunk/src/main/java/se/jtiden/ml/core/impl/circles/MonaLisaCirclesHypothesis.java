package se.jtiden.ml.core.impl.circles;

import java.util.ArrayList;
import java.util.List;

import se.jtiden.ml.core.api.*;
import se.jtiden.ml.core.impl.MonaLisa;

public class MonaLisaCirclesHypothesis implements Hypothesis {

    private final List<CircleWithColor> circles;
    private final MonaLisa monaLisa;
    private Double lossCached;
    private JTImage cachedImage;
    private MonaLisaCirclesAlgorithm algorithm;

    public MonaLisaCirclesHypothesis(
            MonaLisa monaLisa,
            List<CircleWithColor> points, MonaLisaCirclesAlgorithm algorithm) {
        this.monaLisa = monaLisa;
        this.circles = points;
        this.algorithm = algorithm;
    }

    @Override
    public double valueFunction() {
        return innerValueFunction();

        //if (parent == null) {
        //    return innerValueFunction();
        //}
        //return innerValueFunction() - parent.valueFunction();
    }

    public double innerValueFunction() {
        if (lossCached == null) {
            calculateInnerLoss();
        }

        return lossCached;
    }

    private void calculateInnerLoss() {
        Evaluator<JTImage> imageEvaluator = algorithm.getEvaluator();
        lossCached = imageEvaluator.getScore(getCachedImage());
    }


    public List<CircleWithColor> getCircles() {
        return new ArrayList<CircleWithColor>(circles);
    }

    @Override
    public int compareTo(Hypothesis o) {
        if (this == o) {
            return 0;
        }

        return valueFunction() > o.valueFunction() ? 1 : -1;
    }

    public MonaLisa getMonaLisa() {
        return monaLisa;
    }

    public int countCircles() {
        return circles.size();
    }

    public JTImage getCachedImage() {
        if (cachedImage == null) {
            synchronized (this) {
                if (cachedImage == null) {
                    MonaLisaCirclesHypothesisPainter painter = new MonaLisaCirclesHypothesisPainter(this, monaLisa.getWidth(), monaLisa.getHeight());
                    painter.paint();
                }
            }
        }

        return cachedImage;
    }

    public void setCachedImage(JTImage cachedImage) {
        this.cachedImage = cachedImage;
    }
}
