package se.jtiden.ml;

import se.jtiden.ml.core.api.Context;
import se.jtiden.ml.core.api.Hypothesis;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame {
    private Context context;

    protected App(Context context) {
        setSize((int)(context.getHypothesisPainterFactory().getWidth() * context.getScale()),
                (int)(context.getHypothesisPainterFactory().getHeight() * context.getScale()));

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
                                    (int)(image.getWidth(null) * context.getScale()),
                                    (int)(image.getHeight(null) * context.getScale()),
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
