package se.jtiden.ml.imagealgorithms.nearestneighbor;

import se.jtiden.common.images.JTImage;
import se.jtiden.common.images.awt.ImageConverter;
import se.jtiden.ml.imagealgorithms.ContextFactory;
import se.jtiden.ml.imagealgorithms.ContextImpl;
import se.jtiden.ml.imagealgorithms.algorithm.api.IterativeAlgorithm;
import se.jtiden.ml.imagealgorithms.evaluator.DifferenceSquaredEvaluator;
import se.jtiden.ml.imagealgorithms.evaluator.Evaluator;
import se.jtiden.ml.imagealgorithms.original.OriginalContextFactory;
import se.jtiden.ml.imagealgorithms.painter.HypothesisPainterFactory;

public class MonaLisaNearestNeighborContextFactory implements ContextFactory {

    public static final int MUTATION_POINT_VARIANCE = 80;
    public static final int NUM_POINTS = 70;
    public static final double CHANCE_TO_MUTATE_POINT = .80;
    public static final int MUTATION_POINT_COLOR_VARIANCE = 80;
    public static final int FAKE_PIXEL_SIZE = 1;
    private static final double PAINT_FPS = 1;

    @Override
    public ContextImpl getContext() {
        JTImage targetImage = ImageConverter.loadImage(OriginalContextFactory.IMAGE, FAKE_PIXEL_SIZE);
        Evaluator evaluator = new DifferenceSquaredEvaluator(targetImage);
        IterativeAlgorithm algorithm = new MonaLisaNearestNeighborAlgorithm(
                targetImage,
                NUM_POINTS,
                MUTATION_POINT_VARIANCE,
                CHANCE_TO_MUTATE_POINT,
                MUTATION_POINT_COLOR_VARIANCE,
                evaluator);
        final HypothesisPainterFactory hypothesisPainterFactory = new MonaLisaNearestNeighborHypothesisPainterFactory(
                targetImage.getWidth(), targetImage.getHeight());


        return new ContextImpl(
                algorithm,
                hypothesisPainterFactory,
                FAKE_PIXEL_SIZE,
                PAINT_FPS);
    }
}
