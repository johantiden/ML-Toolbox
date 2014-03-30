package se.jtiden.ml;

import se.jtiden.ml.core.api.Context;
import se.jtiden.ml.core.api.Hypothesis;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame {
    public static final int SCALE_UP = 3;
    private Context context;

    protected App(Context context) {
        setSize(context.getHypothesisPainterFactory().getWidth() * SCALE_UP,
                context.getHypothesisPainterFactory().getHeight() * SCALE_UP);

        setVisible(true);
        this.context = context;

        startSimulating();
        startPainting();
    }


    private void startPainting() {
        new Thread(new Runnable() {
            public Hypothesis bestHypothesis;

            @Override
            public void run() {
                while (isVisible()) {
                    try {
                        Thread.sleep(3000);
                        Hypothesis currentHypothesis = context.getAlgorithm().getBestHypothesis();


                        if (currentHypothesis != this.bestHypothesis) {
                            bestHypothesis = currentHypothesis;

                            Graphics g = getGraphics();
                            Image image = context.getHypothesisPainterFactory().create(currentHypothesis).getImage();
                            g.drawImage(image,
                                    0, 0,
                                    image.getWidth(null) * SCALE_UP, image.getHeight(null) * SCALE_UP,
                                    null);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void startSimulating() {
        new Thread(new Runnable() {
            public long step;

            @Override
            public void run() {
                while (isVisible()) {
                    try {
                        Thread.sleep(1);
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
