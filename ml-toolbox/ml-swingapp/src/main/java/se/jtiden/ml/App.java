package se.jtiden.ml;


import com.google.common.util.concurrent.RateLimiter;
import se.jtiden.common.images.awt.ImageConverter;
import se.jtiden.ml.imagealgorithms.Context;
import se.jtiden.ml.imagealgorithms.algorithm.api.Hypothesis;
import se.jtiden.ml.imagealgorithms.algorithm.api.IterativeAlgorithm;
import se.jtiden.ml.imagealgorithms.painter.AlgorithmStepPainter;
import se.jtiden.ml.imagealgorithms.painter.HypothesisPainterFactory;

import javax.swing.*;
import java.awt.*;


public class App extends JFrame {
    private static final long serialVersionUID = -7352504713526579071L;

    private final JPanel canvas;
    private final int imageWidth;
    private final int imageHeight;
    private final IterativeAlgorithm algorithm;
    private final HypothesisPainterFactory hypothesisPainterFactory;
    private Hypothesis bestHypothesis;
    private final double fps;

    protected App(final Context context) {

        algorithm = context.getAlgorithm();
        hypothesisPainterFactory = context.getHypothesisPainterFactory();
        imageWidth = (int) (hypothesisPainterFactory.getWidth() * context.getScale());
        imageHeight = (int) (hypothesisPainterFactory.getHeight() * context.getScale());
        fps = context.getPaintFPS();

        canvas = new PaintPanel();
        canvas.setSize(imageWidth, imageHeight);

        add(canvas);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setSize(imageWidth + 16, imageHeight + 38);

        setVisible(true);
        startSimulating();
        startPainting();
    }


    private void startPainting() {
        new Thread(new PaintRunnable()).start();
    }

    private Graphics graphics() {
        return canvas.getGraphics();
    }

    private void startSimulating() {
        new Thread(new AlgorithmRunnable()).start();
    }

    private class PaintRunnable implements Runnable {


        @Override
        public void run() {
            RateLimiter paintRateLimiter = RateLimiter.create(fps);
            long startTime = System.currentTimeMillis();
            int framesDrawn = 0;
            while (isVisible()) {
                paintRateLimiter.acquire();
                framesDrawn++;
                long totalTime = System.currentTimeMillis() - startTime;
                double meanFPS = framesDrawn / (totalTime / 1000d);

                //System.out.println("frames:"+framesDrawn + " time:"+totalTime + " avg fps:" + meanFPS);
                if (algorithm != null) {
                    Hypothesis currentHypothesis = algorithm.getBestHypothesis();

                    if (currentHypothesis != bestHypothesis) {
                        bestHypothesis = currentHypothesis;
                        canvas.repaint();
                    }
                } else {
                    // There is no algorithm choosing hypotheses, just repaint instead.
                    canvas.repaint();
                }

            }
        }
    }

    private class AlgorithmRunnable implements Runnable {

        @Override
        public void run() {
            if (algorithm != null) {
                while (isVisible()) {
                    try {
                        algorithm.iterate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private class PaintPanel extends JPanel {
        private static final long serialVersionUID = 383854334199089778L;

        @Override
        public void paint(Graphics g) {
            super.paint(g);

            AlgorithmStepPainter algorithmStepPainter = hypothesisPainterFactory.create(bestHypothesis);
            Image image = ImageConverter.toAwtImage(algorithmStepPainter.getImage());
            g.drawImage(image,
                    0, 0,
                    imageWidth,
                    imageHeight,
                    null);

        }
    }
}
