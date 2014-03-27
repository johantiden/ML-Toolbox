package se.jtiden.ml.core.impl.nearestneighbor;

import se.jtiden.ml.core.api.HypothesisPainterFactory;
import se.jtiden.ml.core.api.IterativeAlgorithm;
import se.jtiden.ml.core.impl.ContextImpl;
import se.jtiden.ml.core.impl.MonaLisa;
import se.jtiden.ml.core.impl.MonaLisaAlgorithmPainter;
import se.jtiden.ml.core.impl.MonaLisaPainter;

public class MonaLisaNearestNeighborContextFactory {

    public static final int MUTATION_POINT_VARIANCE = 80;
    public static final int NUM_NEIGHBORS_FOR_CLASSIFICATION = 2;
    public static final int NUM_POINTS = 70;
    public static final int NUM_HYPOTHESES = 1;
    public static final int ALPHA = 100;
    public static final int DOT_RADIUS = 1;
    public static final double CHANCE_TO_MUTATE_POINT = .80;
    public static final int FAKE_PIXEL_SIZE = 4;
    public static final int MUTATION_POINT_COLOR_VARIANCE = 80;

    public ContextImpl getContext() {
        MonaLisa monaLisa = new MonaLisa();
        IterativeAlgorithm algorithm = new MonaLisaNearestNeighborAlgorithm(
                monaLisa,
                NUM_HYPOTHESES,
                NUM_POINTS,
                MUTATION_POINT_VARIANCE,
                NUM_NEIGHBORS_FOR_CLASSIFICATION,
                CHANCE_TO_MUTATE_POINT,
                MUTATION_POINT_COLOR_VARIANCE);
        final HypothesisPainterFactory hypothesisPainterFactory = new MonaLisaNearestNeighborHypothesisPainterFactory(
                ALPHA,
                FAKE_PIXEL_SIZE);


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
