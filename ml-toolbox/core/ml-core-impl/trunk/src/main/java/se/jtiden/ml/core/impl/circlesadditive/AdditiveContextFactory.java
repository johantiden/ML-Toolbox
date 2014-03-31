package se.jtiden.ml.core.impl.circlesadditive;

import se.jtiden.ml.core.api.HypothesisPainterFactory;
import se.jtiden.ml.core.api.IterativeAlgorithm;
import se.jtiden.ml.core.impl.ContextImpl;
import se.jtiden.ml.core.impl.DifferenceSquaredEvaluator;
import se.jtiden.ml.core.impl.MonaLisa;
import se.jtiden.ml.core.impl.MonaLisaAlgorithmPainter;

public class AdditiveContextFactory {

    public static final double FAKE_PIXEL_SIZE = 5;


    public ContextImpl getContext() {
        MonaLisa monaLisa = new MonaLisa(FAKE_PIXEL_SIZE);
        DifferenceSquaredEvaluator evaluator = new DifferenceSquaredEvaluator(monaLisa.getImage());
        IterativeAlgorithm algorithm = new AddititveAlgorithm(
                monaLisa,
                evaluator);
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
                FAKE_PIXEL_SIZE);
    }
}
