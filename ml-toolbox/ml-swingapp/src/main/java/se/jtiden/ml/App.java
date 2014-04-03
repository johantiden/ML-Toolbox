package se.jtiden.ml;


import se.jtiden.common.images.awt.ImageConverter;
import se.jtiden.ml.imagealgorithms.Context;
import se.jtiden.ml.imagealgorithms.algorithm.api.Hypothesis;
import se.jtiden.ml.imagealgorithms.algorithm.api.IterativeAlgorithm;

import javax.swing.*;
import java.awt.*;


public class App extends JFrame {
    private final JPanel canvas;
    private final Context context;
    public Hypothesis bestHypothesis;

    protected App(final Context context) {


        this.context = context;


        this.canvas = new JPanel() {
            @Override
            public void paint(Graphics g) {
                Image image = ImageConverter.toAwtImage(context.getHypothesisPainterFactory().create(bestHypothesis).getImage());
                g.drawImage(image,
                        0, 0,
                        (int) (image.getWidth(null) * context.getScale()),
                        (int) (image.getHeight(null) * context.getScale()),
                        null);
            }
        };
        this.canvas.setSize(imageWidth(), imageHeight());

        this.add(canvas);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setSize(imageWidth() + 16, imageHeight() + 38);

        setVisible(true);
        startSimulating();
        startPainting();
    }

    private int imageHeight() {
        return (int) (context.getHypothesisPainterFactory().getHeight() * context.getScale());
    }

    private int imageWidth() {
        return (int) (context.getHypothesisPainterFactory().getWidth() * context.getScale());
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
            while (isVisible()) {
                try {
                    Thread.sleep(3000);
                    IterativeAlgorithm algorithm = context.getAlgorithm();
                    Hypothesis currentHypothesis = algorithm.getBestHypothesis();

                    if (currentHypothesis != bestHypothesis) {
                        bestHypothesis = currentHypothesis;
                        canvas.repaint();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class AlgorithmRunnable implements Runnable {

        @Override
        public void run() {
            while (isVisible()) {
                try {
                    Thread.yield();
                    IterativeAlgorithm algorithm = context.getAlgorithm();
                    algorithm.iterate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
