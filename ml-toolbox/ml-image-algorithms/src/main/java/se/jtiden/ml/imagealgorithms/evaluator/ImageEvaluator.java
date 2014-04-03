package se.jtiden.ml.imagealgorithms.evaluator;

import se.jtiden.common.images.JTImage;

public interface ImageEvaluator extends Evaluator<JTImage> {
    public double getScore(JTImage image);
}
