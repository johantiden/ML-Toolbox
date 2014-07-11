package se.jtiden.ml.imagealgorithms.circlesadditive;

import se.jtiden.common.images.JTImage;
import se.jtiden.common.images.awt.ImageConverter;
import se.jtiden.ml.imagealgorithms.Context;
import se.jtiden.ml.imagealgorithms.ContextFactory;
import se.jtiden.ml.imagealgorithms.ContextImpl;
import se.jtiden.ml.imagealgorithms.algorithm.api.IterativeAlgorithm;
import se.jtiden.ml.imagealgorithms.evaluator.DifferenceSquaredEvaluator;
import se.jtiden.ml.imagealgorithms.evaluator.Evaluator;
import se.jtiden.ml.imagealgorithms.original.OriginalContextFactory;
import se.jtiden.ml.imagealgorithms.painter.HypothesisPainterFactory;

public class AdditiveContextFactory implements ContextFactory {

    public static final double SCALE_DOWN_BEFORE = OriginalContextFactory.SCALE_DOWN_BEFORE;
    public static final double SCALE_UP_AFTER = OriginalContextFactory.SCALE_UP_AFTER;
    public static final int BASE_ALPHA = 250;
    public static final int MIN_RADIUS = 3;
    private static final double PAINT_FPS = 1;

    @Override
    public Context getContext() {
        JTImage targetImage = ImageConverter.loadImage(OriginalContextFactory.IMAGE, SCALE_DOWN_BEFORE);
        Evaluator<JTImage> evaluator = new DifferenceSquaredEvaluator(targetImage);
        IterativeAlgorithm algorithm = new AdditiveAlgorithm(
                evaluator);
        HypothesisPainterFactory hypothesisPainterFactory = new AdditiveHypothesisPainterFactory(
                targetImage.getWidth(), targetImage.getHeight());


        return new ContextImpl(
                algorithm,
                hypothesisPainterFactory,
                SCALE_UP_AFTER,
                PAINT_FPS);
    }
}
