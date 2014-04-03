package se.jtiden.ml.core.evaluator;

import se.jtiden.common.images.JTImage;

public interface ImageEvaluator extends Evaluator<JTImage> {
    public double getScore(JTImage image);
}
