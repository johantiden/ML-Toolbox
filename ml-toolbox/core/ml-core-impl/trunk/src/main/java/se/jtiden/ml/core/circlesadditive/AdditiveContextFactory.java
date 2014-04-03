package se.jtiden.ml.core.circlesadditive;

import se.jtiden.ml.core.painter.HypothesisPainterFactory;
import se.jtiden.ml.core.algorithm.api.IterativeAlgorithm;
import se.jtiden.ml.core.ContextImpl;
import se.jtiden.ml.core.evaluator.DifferenceSquaredEvaluator;
import se.jtiden.ml.core.MonaLisa;
import se.jtiden.ml.core.MonaLisaAlgorithmPainter;
import se.jtiden.ml.core.OriginalContextFactory;

public class AdditiveContextFactory {

    public static final double SCALE_DOWN_BEFORE = OriginalContextFactory.SCALE_DOWN_BEFORE;
    public static final double SCALE_UP_AFTER = OriginalContextFactory.SCALE_UP_AFTER;
    public static final int BASE_ALPHA = 250;
    public static final int MIN_RADIUS = 3;


    public ContextImpl getContext() {
        MonaLisa monaLisa = new MonaLisa(SCALE_DOWN_BEFORE);
        DifferenceSquaredEvaluator evaluator = new DifferenceSquaredEvaluator(monaLisa.getImage());
        IterativeAlgorithm algorithm = new AddititveAlgorithm(
                monaLisa,
                evaluator, BASE_ALPHA, MIN_RADIUS);
        final HypothesisPainterFactory hypothesisPainterFactory = new AdditiveHypothesisPainterFactory(
                monaLisa.getWidth(), monaLisa.getHeight());


        return new ContextImpl(
                algorithm,
                new MonaLisaAlgorithmPainter(
                        algorithm,
                        hypothesisPainterFactory)
//                )
                ,
                hypothesisPainterFactory,
                SCALE_UP_AFTER);
    }
}
