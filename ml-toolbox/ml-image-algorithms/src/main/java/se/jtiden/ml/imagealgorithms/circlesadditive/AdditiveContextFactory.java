package se.jtiden.ml.imagealgorithms.circlesadditive;

import se.jtiden.ml.imagealgorithms.ContextImpl;
import se.jtiden.ml.imagealgorithms.MonaLisa;
import se.jtiden.ml.imagealgorithms.MonaLisaAlgorithmPainter;
import se.jtiden.ml.imagealgorithms.algorithm.api.IterativeAlgorithm;
import se.jtiden.ml.imagealgorithms.evaluator.DifferenceSquaredEvaluator;
import se.jtiden.ml.imagealgorithms.original.OriginalContextFactory;
import se.jtiden.ml.imagealgorithms.painter.HypothesisPainterFactory;

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
