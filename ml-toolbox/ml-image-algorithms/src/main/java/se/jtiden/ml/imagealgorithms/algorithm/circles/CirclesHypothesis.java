package se.jtiden.ml.imagealgorithms.algorithm.circles;

import java.util.ArrayList;
import java.util.List;

import se.jtiden.common.images.CircleWithColor;
import se.jtiden.common.images.JTImage;
import se.jtiden.ml.imagealgorithms.MonaLisa;
import se.jtiden.ml.imagealgorithms.algorithm.api.Hypothesis;
import se.jtiden.ml.imagealgorithms.evaluator.Evaluator;

public class CirclesHypothesis implements Hypothesis {

    private final List<CircleWithColor> circles;
    private final Evaluator<JTImage> imageEvaluator;
    private final MonaLisa monaLisa;
    private Double lossCached;
    private JTImage cachedImage;

    public CirclesHypothesis(
            MonaLisa monaLisa,
            List<CircleWithColor> points, Evaluator<JTImage> imageEvaluator) {
        this.monaLisa = monaLisa;
        this.circles = points;
        this.imageEvaluator = imageEvaluator;
    }

    @Override
    public double valueFunction() {
        return innerValueFunction();
    }

    public double innerValueFunction() {
        if (lossCached == null) {
            calculateInnerLoss();
        }

        return lossCached;
    }

    private void calculateInnerLoss() {
        lossCached = imageEvaluator.getScore(getCachedImage());
        lossCached -= circles.size();
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
                    CirclesHypothesisPainter painter = new CirclesHypothesisPainter(this, monaLisa.getWidth(), monaLisa.getHeight());
                    painter.paint();
                }
            }
        }

        return cachedImage;
    }

    public void setCachedImage(JTImage cachedImage) {
        this.cachedImage = cachedImage;
    }

    public List<CircleWithColor> copyPoints() {
        return new ArrayList<CircleWithColor>(circles);
    }

    public CirclesHypothesis copy() {
        return new CirclesHypothesis(monaLisa, copyPoints(), imageEvaluator);
    }
}
