package se.jtiden.ml.core.api;

public interface ImageEvaluator extends Evaluator<JTImage> {
    public double getScore(JTImage image);
}
