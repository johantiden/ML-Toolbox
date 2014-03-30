package se.jtiden.ml.core.impl.nearestneighbor;

import se.jtiden.ml.core.api.DifferenceSquaredEvaluator;
import se.jtiden.ml.core.api.HypothesisPainterFactory;
import se.jtiden.ml.core.api.IterativeAlgorithm;
import se.jtiden.ml.core.impl.ContextImpl;
import se.jtiden.ml.core.impl.MonaLisa;
import se.jtiden.ml.core.impl.MonaLisaAlgorithmPainter;

public class MonaLisaNearestNeighborContextFactory {

    public static final int MUTATION_POINT_VARIANCE = 80;
    public static final int NUM_POINTS = 70;
    public static final double CHANCE_TO_MUTATE_POINT = .80;
    public static final int MUTATION_POINT_COLOR_VARIANCE = 80;

    public ContextImpl getContext() {
        MonaLisa monaLisa = new MonaLisa();
        DifferenceSquaredEvaluator evaluator = new DifferenceSquaredEvaluator(monaLisa.getImage());
        IterativeAlgorithm algorithm = new MonaLisaNearestNeighborAlgorithm(
                monaLisa,
                NUM_POINTS,
                MUTATION_POINT_VARIANCE,
                CHANCE_TO_MUTATE_POINT,
                MUTATION_POINT_COLOR_VARIANCE,
                evaluator);
        final HypothesisPainterFactory hypothesisPainterFactory = new MonaLisaNearestNeighborHypothesisPainterFactory(
                monaLisa.getWidth(), monaLisa.getHeight());


        return new ContextImpl(
                algorithm,
                new MonaLisaAlgorithmPainter(
                        algorithm,
                        hypothesisPainterFactory)
//                )
                ,
                hypothesisPainterFactory);
    }
}
