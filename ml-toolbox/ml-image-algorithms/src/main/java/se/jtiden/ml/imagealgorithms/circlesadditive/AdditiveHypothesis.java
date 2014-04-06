package se.jtiden.ml.imagealgorithms.circlesadditive;

import se.jtiden.common.images.CircleWithColor;
import se.jtiden.common.images.JTImage;
import se.jtiden.ml.imagealgorithms.algorithm.api.Hypothesis;
import se.jtiden.ml.imagealgorithms.evaluator.Evaluator;

public class AdditiveHypothesis implements Hypothesis {

    private final JTImage image;
    private final Evaluator<JTImage> imageEvaluator;
    private Double lossCached;

    public AdditiveHypothesis(
            JTImage image,
            Evaluator<JTImage> imageEvaluator) {
        this.image = image;
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
        lossCached = imageEvaluator.getScore(image);
    }


    @Override
    public int compareTo(Hypothesis o) {
        if (this == o) {
            return 0;
        }

        return valueFunction() > o.valueFunction() ? 1 : -1;
    }


    public AdditiveHypothesis copy() {
        return new AdditiveHypothesis(image.copy(), imageEvaluator);
    }

    public void draw(CircleWithColor circleWithColor) {
        image.getGraphics().drawRadial(circleWithColor);
    }

    public JTImage getImage() {
        return image;
    }
}
