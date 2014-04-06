package se.jtiden.ml.imagealgorithms.original;

import se.jtiden.common.images.JTImage;
import se.jtiden.common.images.awt.ImageConverter;
import se.jtiden.ml.imagealgorithms.Context;
import se.jtiden.ml.imagealgorithms.ContextFactory;
import se.jtiden.ml.imagealgorithms.ContextImpl;
import se.jtiden.ml.imagealgorithms.algorithm.api.Hypothesis;
import se.jtiden.ml.imagealgorithms.algorithm.api.IterativeAlgorithm;
import se.jtiden.ml.imagealgorithms.evaluator.Evaluator;
import se.jtiden.ml.imagealgorithms.painter.AlgorithmStepPainter;
import se.jtiden.ml.imagealgorithms.painter.HypothesisPainterFactory;

public class OriginalContextFactory implements ContextFactory {

    public static final String RGB = "rgb.gif";
    public static final String MONALISA = "monalisa.jpg";
    public static final String LOVE = "monalisa3.jpg";
    public static final String STRANDED = "stranden.jpg";
    public static final String JAPAN = "japan.jpg";


    public static final double SCALE_DOWN_BEFORE = 2;
    public static final double SCALE_UP_AFTER = 2;
    public static final String IMAGE = MONALISA;

    @Override
    public Context getContext() {
        JTImage targetImage = ImageConverter.loadImage(OriginalContextFactory.IMAGE, SCALE_DOWN_BEFORE);

        AlgorithmStepPainter painter = new Painter(targetImage);
        return new ContextImpl(
                new NoAlgorithm(targetImage),
                painter
                ,
                new MyHypothesisPainterFactory(painter, targetImage),
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
        private final JTImage targetImage;

        public MyHypothesisPainterFactory(AlgorithmStepPainter painter, JTImage targetImage) {
            this.painter = painter;
            this.targetImage = targetImage;
        }

        @Override
        public AlgorithmStepPainter create(Hypothesis hypothesis) {
            return painter;
        }

        @Override
        public int getWidth() {
            return targetImage.getWidth();
        }

        @Override
        public int getHeight() {
            return targetImage.getHeight();
        }
    }

    private static class NoAlgorithm implements IterativeAlgorithm {
        private final JTImage targetImage;

        public NoAlgorithm(JTImage targetImage) {

            this.targetImage = targetImage;
        }

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

        private class NoEvaluator implements Evaluator {
            @Override
            public double getScore(Object o) {
                return 0;
            }

            @Override
            public Object getTarget() {
                return null;
            }

            @Override
            public int getWidth() {
                return targetImage.getWidth();
            }

            @Override
            public int getHeight() {
                return targetImage.getHeight();
            }
        }
    }

    private static class Painter implements AlgorithmStepPainter {
        private final JTImage targetImage;

        public Painter(JTImage targetImage) {
            this.targetImage = targetImage;
        }

        @Override
        public void paint() {
        }

        @Override
        public JTImage getImage() {
            return targetImage;
        }

        @Override
        public int getWidth() {
            return targetImage.getWidth();
        }

        @Override
        public int getHeight() {
            return targetImage.getHeight();
        }
    }
}
