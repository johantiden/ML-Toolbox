package se.jtiden.ml.imagealgorithms.original;

import se.jtiden.common.images.JTImage;
import se.jtiden.common.images.awt.ImageConverter;
import se.jtiden.ml.imagealgorithms.Context;
import se.jtiden.ml.imagealgorithms.ContextFactory;
import se.jtiden.ml.imagealgorithms.ContextImpl;
import se.jtiden.ml.imagealgorithms.algorithm.api.Hypothesis;
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
        JTImage targetImage = ImageConverter.loadImage(IMAGE, SCALE_DOWN_BEFORE);

        AlgorithmStepPainter painter = new OriginalPainter(targetImage);
        return new ContextImpl(
                null,
                new MyHypothesisPainterFactory(painter, targetImage),
                SCALE_UP_AFTER,
                1
        );
    }

    private static class MyHypothesisPainterFactory implements HypothesisPainterFactory {
        private static final long serialVersionUID = -5835420848057327033L;
        private final AlgorithmStepPainter painter;
        private final JTImage targetImage;

        private MyHypothesisPainterFactory(AlgorithmStepPainter painter, JTImage targetImage) {
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

    private static class OriginalPainter implements AlgorithmStepPainter {
        private static final long serialVersionUID = 7824946110402526373L;
        private final JTImage targetImage;

        private OriginalPainter(JTImage targetImage) {
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
