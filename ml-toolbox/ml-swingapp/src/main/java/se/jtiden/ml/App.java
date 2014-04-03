package se.jtiden.ml;


import java.awt.*;
import javax.swing.*;

import se.jtiden.ml.imagealgorithms.Context;
import se.jtiden.ml.imagealgorithms.algorithm.api.Hypothesis;


public class App extends JFrame {
    private final JPanel canvas;
    private final Context context;
    public Hypothesis bestHypothesis;

    protected App(final Context context) {


        this.context = context;


        this.canvas = new JPanel() {
            @Override
            public void paint(Graphics g) {
                Image image = context.getHypothesisPainterFactory().create(bestHypothesis).getImage().getImageConverter().getAwtImage();
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
        new Thread(new Runnable() {


            @Override
            public void run() {
                while (isVisible()) {
                    try {
                        Thread.sleep(3000);
                        Hypothesis currentHypothesis = context.getAlgorithm().getBestHypothesis();

                        if (currentHypothesis != bestHypothesis) {
                            bestHypothesis = currentHypothesis;
                            canvas.repaint();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private Graphics graphics() {
        return canvas.getGraphics();
    }

    private void startSimulating() {
        new Thread(new Runnable() {
            public long step;

            @Override
            public void run() {
                while (isVisible()) {
                    try {
                        Thread.yield();
                        //System.out.println("Step " + (step++));
                        context.getAlgorithm().iterate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
