package se.jtiden.ml.core.impl.circlesadditive;

import se.jtiden.ml.core.api.HypothesisPainterFactory;
import se.jtiden.ml.core.api.IterativeAlgorithm;
import se.jtiden.ml.core.impl.ContextImpl;
import se.jtiden.ml.core.impl.DifferenceSquaredEvaluator;
import se.jtiden.ml.core.impl.MonaLisa;
import se.jtiden.ml.core.impl.MonaLisaAlgorithmPainter;
import se.jtiden.ml.core.impl.original.OriginalContextFactory;

public class AdditiveContextFactory {

    public static final double SCALE_DOWN_BEFORE = OriginalContextFactory.SCALE_DOWN_BEFORE;
    public static final double SCALE_UP_AFTER = OriginalContextFactory.SCALE_UP_AFTER;
    public static final int BASE_ALPHA = 240;


    public ContextImpl getContext() {
        MonaLisa monaLisa = new MonaLisa(SCALE_DOWN_BEFORE);
        DifferenceSquaredEvaluator evaluator = new DifferenceSquaredEvaluator(monaLisa.getImage());
        IterativeAlgorithm algorithm = new AddititveAlgorithm(
                monaLisa,
                evaluator, BASE_ALPHA);
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
