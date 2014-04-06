package se.jtiden.ml.imagealgorithms.circles;

import se.jtiden.common.images.JTImage;
import se.jtiden.common.images.awt.ImageConverter;
import se.jtiden.ml.imagealgorithms.ContextImpl;
import se.jtiden.ml.imagealgorithms.MonaLisaAlgorithmPainter;
import se.jtiden.ml.imagealgorithms.algorithm.api.IterativeAlgorithm;
import se.jtiden.ml.imagealgorithms.evaluator.DifferenceSquaredEvaluator;
import se.jtiden.ml.imagealgorithms.original.OriginalContextFactory;
import se.jtiden.ml.imagealgorithms.painter.HypothesisPainterFactory;

public class CirclesContextFactory {

    public static final int MUTATION_POINT_VARIANCE = 100;
    public static final int MIN_NUM_POINTS = 1;
    public static final int MAX_NUM_POINTS = 2000;
    public static final double CHANCE_TO_MUTATE_POINT = .5;
    public static final double CHANCE_TO_CREATE_POINT = 0.1;
    public static final double CHANCE_TO_DELETE_POINT = 0.01;
    public static final int MUTATION_POINT_COLOR_VARIANCE = 200;
    public static final int MUTATION_RADIUS_VARIANCE = 200;
    public static final int FAKE_PIXEL_SIZE = 5;


    public ContextImpl getContext() {
        JTImage monaLisa = ImageConverter.loadImage(OriginalContextFactory.IMAGE, FAKE_PIXEL_SIZE);
        DifferenceSquaredEvaluator evaluator = new DifferenceSquaredEvaluator(monaLisa);
        IterativeAlgorithm algorithm = new CirclesAlgorithm(
                monaLisa,
                MIN_NUM_POINTS,
                MAX_NUM_POINTS,
                MUTATION_POINT_VARIANCE,
                CHANCE_TO_MUTATE_POINT,
                CHANCE_TO_CREATE_POINT,
                CHANCE_TO_DELETE_POINT,
                MUTATION_POINT_COLOR_VARIANCE,
                MUTATION_RADIUS_VARIANCE,
                evaluator);
        final HypothesisPainterFactory hypothesisPainterFactory = new CirclesHypothesisPainterFactory(
                monaLisa.getWidth(), monaLisa.getHeight());


        return new ContextImpl(
                algorithm,
                new MonaLisaAlgorithmPainter(
                        algorithm,
                        hypothesisPainterFactory)
//                )
                ,
                hypothesisPainterFactory,
                FAKE_PIXEL_SIZE);
    }
}
