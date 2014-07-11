package se.jtiden.ml.triplecircle;

import com.sun.istack.internal.NotNull;
import org.apache.commons.lang.NotImplementedException;
import se.jtiden.common.images.*;
import se.jtiden.ml.imagealgorithms.Context;
import se.jtiden.ml.imagealgorithms.ContextFactory;
import se.jtiden.ml.imagealgorithms.ContextImpl;
import se.jtiden.ml.imagealgorithms.algorithm.api.Hypothesis;
import se.jtiden.ml.imagealgorithms.painter.AlgorithmStepPainter;
import se.jtiden.ml.imagealgorithms.painter.HypothesisPainterFactory;

public class TripleCircleLoadingAnimationContextFactory implements ContextFactory {
    private static final int WIDTH = 100;
    private static final int HEIGHT = 100;
    public static final int SCALE = 3;
    private static final double PAINT_FPS = 100;

    @Override
    public Context getContext() {
        HypothesisPainterFactory hypothesisPainterFactory = new TripleCircleLoadingPainterFactory();

        return new ContextImpl(
                null,
                hypothesisPainterFactory,
                SCALE,
                PAINT_FPS);
    }

    private static class TripleCircleLoadingPainter implements AlgorithmStepPainter {

        private static final long serialVersionUID = -7717714536012321976L;

        private static final double ANIMATION_LENGTH = 8; //seconds

        @Override
        public void paint() {
            throw new NotImplementedException();
        }

        @Override
        public JTImage getImage() {
            JTImage image = new FastJTImage(WIDTH, HEIGHT);
            JTGraphics g = image.getGraphics();

            double percentageOfAnimation = System.currentTimeMillis() % (ANIMATION_LENGTH*1000) / (ANIMATION_LENGTH*1000);
            paintAnimation(g, percentageOfAnimation);
            return image;
        }

        private static void paintAnimation(JTGraphics g, double percentageOfAnimation) {
            g.fillAll(JTColor.WHITE);

            double innerMagnitude = 25;
            double outerMagnitude = 15;

            double centerX = WIDTH/2d;
            double centerY = HEIGHT/2d;

            drawCircle(g, centerX, centerY, outerMagnitude + innerMagnitude);

            //double x1 = centerX + StrictMath.cos(percentageOfAnimation*Math.PI*2) * outerMagnitude;
            //double y1 = centerY + StrictMath.sin(percentageOfAnimation*Math.PI*2) * outerMagnitude;

            //drawCircle(g, x1, y1, innerMagnitude);
            //drawCircle(g, x1, y1, 2);
            drawInner(g, centerX, centerY, percentageOfAnimation, innerMagnitude, 0, JTColor.RED, outerMagnitude, 0);

            drawInner(g, centerX, centerY, percentageOfAnimation+ 1d/3d % 1d, innerMagnitude, 0, JTColor.GREEN, outerMagnitude, 1d/3d);

            drawInner(g, centerX, centerY, percentageOfAnimation+ 2d/3d % 1d, innerMagnitude, 0, JTColor.BLUE, outerMagnitude, 2d/3d);

            System.out.println(percentageOfAnimation);

            //double middleRadius = 40 * (1 + Math.sin(3 * (1d / 12d + percentageOfAnimation) * Math.PI * 2));
            //drawCircle(g, centerX, centerY, middleRadius/2);
            //g.drawRadial(new CircleWithColorImpl(centerX, centerY,
            //        new JTColorImpl(JTColor.YELLOW, 255),
            //        middleRadius));
        }

        private static void drawCircle(JTGraphics g, double x, double y, double radius) {
            //g.drawCircle(new CircleWithColorImpl(x, y,
            //        JTColor.BLACK,
            //        radius), 1d);
        }

        private static void drawInner(JTGraphics g, double centerX, double centerY, double percentageOfAnimation, double magnitude, double percentInner, JTColor baseColor, double outerMagnitude, double baseAngleOffset) {
            double x = centerX + Math.cos(4* percentageOfAnimation *Math.PI*2) * magnitude + Math.cos((baseAngleOffset + percentageOfAnimation) * Math.PI*2) * outerMagnitude;
            double y = centerY + Math.sin(4* percentageOfAnimation *Math.PI*2) * magnitude + Math.sin((baseAngleOffset + percentageOfAnimation) * Math.PI * 2) * outerMagnitude;

            g.fillCircle(new CircleWithColorImpl(
                    x, y,
                    new JTColorImpl(
                            baseColor,
                            (int) (255 * (1 - percentInner / 2))),
                    5 * (1 - percentInner / 2)));

            if (percentInner < 0.95) {
                double step = 0.01;
                drawInner(g, centerX, centerY, percentageOfAnimation - step/8d, magnitude, percentInner + step, baseColor, outerMagnitude, baseAngleOffset);
            }
        }

        @Override
        public int getWidth() {
            return WIDTH;
        }

        @Override
        public int getHeight() {
            return HEIGHT;
        }
    }

    private static class TripleCircleLoadingPainterFactory implements HypothesisPainterFactory {
        private static final long serialVersionUID = 3800131644657125272L;
        private AlgorithmStepPainter painter = new TripleCircleLoadingPainter();

        @Override
        public AlgorithmStepPainter create(@NotNull Hypothesis hypothesis) {
            return painter;
        }

        @Override
        public int getWidth() {
            return WIDTH;
        }

        @Override
        public int getHeight() {
            return HEIGHT;
        }
    }
}
