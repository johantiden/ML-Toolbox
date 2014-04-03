package se.jtiden.ml.imagealgorithms.original;

import se.jtiden.common.images.JTImage;
import se.jtiden.ml.imagealgorithms.Context;
import se.jtiden.ml.imagealgorithms.ContextFactory;
import se.jtiden.ml.imagealgorithms.ContextImpl;
import se.jtiden.ml.imagealgorithms.MonaLisa;
import se.jtiden.ml.imagealgorithms.algorithm.api.Hypothesis;
import se.jtiden.ml.imagealgorithms.algorithm.api.IterativeAlgorithm;
import se.jtiden.ml.imagealgorithms.evaluator.Evaluator;
import se.jtiden.ml.imagealgorithms.painter.AlgorithmStepPainter;
import se.jtiden.ml.imagealgorithms.painter.HypothesisPainterFactory;

public class OriginalContextFactory implements ContextFactory {

    public static final double SCALE_DOWN_BEFORE = 1;
    public static final double SCALE_UP_AFTER = 2;

    @Override
    public Context getContext() {
        MonaLisa monaLisa = new MonaLisa(SCALE_DOWN_BEFORE);

        AlgorithmStepPainter painter = new Painter(monaLisa);
        return new ContextImpl(
                new NoAlgorithm(),
                painter
                ,
                new MyHypothesisPainterFactory(painter, monaLisa),
                SCALE_UP_AFTER
        );
    }

    private static class NoHypothesis implements Hypothesis {
        @Override
        public double valueFunction() {
            return 0;
        }

        @Override
        public Hypothesis copy() {
            return this; // :)
        }

        @Override
        public int compareTo(Hypothesis o) {
            return 0;
        }
    }

    private static class MyHypothesisPainterFactory implements HypothesisPainterFactory {
        private final AlgorithmStepPainter painter;
        private final MonaLisa monaLisa;

        public MyHypothesisPainterFactory(AlgorithmStepPainter painter, MonaLisa monaLisa) {
            this.painter = painter;
            this.monaLisa = monaLisa;
        }

        @Override
        public AlgorithmStepPainter create(Hypothesis hypothesis) {
            return painter;
        }

        @Override
        public int getWidth() {
            return monaLisa.getWidth();
        }

        @Override
        public int getHeight() {
            return monaLisa.getHeight();
        }
    }

    private static class NoAlgorithm implements IterativeAlgorithm {
        @Override
        public void iterate() {

        }

        @Override
        public Hypothesis getBestHypothesis() {
            return new NoHypothesis();
        }

        @Override
        public Evaluator getEvaluator() {
            return new NoEvaluator();
        }

        private static class NoEvaluator implements Evaluator {
            @Override
            public double getScore(Object o) {
                return 0;
            }

            @Override
            public Object getTarget() {
                return null;
            }
        }
    }

    private static class Painter implements AlgorithmStepPainter {
        private final MonaLisa monaLisa;

        public Painter(MonaLisa monaLisa) {
            this.monaLisa = monaLisa;
        }

        @Override
        public void paint() {
        }

        @Override
        public JTImage getImage() {
            return monaLisa.getImage();
        }

        @Override
        public int getWidth() {
            return monaLisa.getWidth();
        }

        @Override
        public int getHeight() {
            return monaLisa.getHeight();
        }
    }
}
