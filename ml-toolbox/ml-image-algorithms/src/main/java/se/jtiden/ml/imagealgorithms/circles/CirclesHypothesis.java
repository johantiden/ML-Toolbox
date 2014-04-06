package se.jtiden.ml.imagealgorithms.circles;

import se.jtiden.common.images.CircleWithColor;
import se.jtiden.common.images.JTImage;
import se.jtiden.ml.imagealgorithms.algorithm.api.Hypothesis;
import se.jtiden.ml.imagealgorithms.evaluator.Evaluator;

import java.util.ArrayList;
import java.util.List;

public class CirclesHypothesis implements Hypothesis {

    private final List<CircleWithColor> circles;
    private final Evaluator<JTImage> imageEvaluator;
    private Double lossCached;
    private JTImage cachedImage;

    public CirclesHypothesis(
            List<CircleWithColor> circles, Evaluator<JTImage> imageEvaluator) {
        this.circles = circles;
        this.imageEvaluator = imageEvaluator;
    }

    @Override
    public double valueFunction() {
        return innerValueFunction();
    }

    public double innerValueFunction() {
        if (lossCached == null) {
            lossCached = imageEvaluator.getScore(getCachedImage());
        }

        return lossCached;
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

    public JTImage getTargetImage() {
        return imageEvaluator.getTarget();
    }

    public int countCircles() {
        return circles.size();
    }

    public JTImage getCachedImage() {
        if (cachedImage == null) {
            synchronized (this) {
                if (cachedImage == null) {
                    CirclesHypothesisPainter painter = new CirclesHypothesisPainter(this, imageEvaluator.getWidth(), imageEvaluator.getHeight());
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
        return new CirclesHypothesis(copyPoints(), imageEvaluator);
    }
}
