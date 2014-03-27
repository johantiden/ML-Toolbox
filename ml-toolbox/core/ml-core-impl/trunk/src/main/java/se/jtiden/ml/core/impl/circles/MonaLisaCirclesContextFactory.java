package se.jtiden.ml.core.impl.circles;

import se.jtiden.ml.core.api.HypothesisPainterFactory;
import se.jtiden.ml.core.api.IterativeAlgorithm;
import se.jtiden.ml.core.impl.ContextImpl;
import se.jtiden.ml.core.impl.MonaLisa;
import se.jtiden.ml.core.impl.MonaLisaAlgorithmPainter;
import se.jtiden.ml.core.impl.MonaLisaPainter;

public class MonaLisaCirclesContextFactory {

    public static final int MUTATION_POINT_VARIANCE = 80;
    public static final int MIN_NUM_POINTS = 1;
    public static final int MAX_NUM_POINTS = 2000;
    public static final int ALPHA = 100;
    public static final double CHANCE_TO_MUTATE_POINT = .30;
    public static final int FAKE_PIXEL_SIZE = 2;
    public static final int MUTATION_POINT_COLOR_VARIANCE = 60;
    public static final int MUTATION_RADIUS_VARIANCE = 80;
    public static final double CHANCE_TO_CREATE_POINT = 0.005;

    public ContextImpl getContext() {
        MonaLisa monaLisa = new MonaLisa();
        IterativeAlgorithm algorithm = new MonaLisaCirclesAlgorithm(
                monaLisa,
                MIN_NUM_POINTS,
                MAX_NUM_POINTS,
                MUTATION_POINT_VARIANCE,
                CHANCE_TO_MUTATE_POINT,
                CHANCE_TO_CREATE_POINT,
                MUTATION_POINT_COLOR_VARIANCE,
                MUTATION_RADIUS_VARIANCE);
        final HypothesisPainterFactory hypothesisPainterFactory = new MonaLisaCirclesHypothesisPainterFactory(
                ALPHA,
                FAKE_PIXEL_SIZE, monaLisa.getWidth(), monaLisa.getHeight());


        return new ContextImpl(
                algorithm,
//                new DotPainter(
//                        new DotProducerBestMonaLisaHypothesis(algorithm),
//                        DOT_RADIUS,
                        new MonaLisaAlgorithmPainter(
                                algorithm,
                                new MonaLisaPainter(monaLisa, null),
                                hypothesisPainterFactory)
//                )
        );
    }
}
