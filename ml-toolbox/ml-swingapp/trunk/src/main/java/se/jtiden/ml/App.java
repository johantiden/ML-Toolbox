package se.jtiden.ml;

import se.jtiden.ml.core.api.Context;
import se.jtiden.ml.core.api.Hypothesis;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class App extends JFrame {
    private Context context;

    protected App(Context context) {
        setSize(context.getPainter().getWidth(),
                context.getPainter().getHeight());

        setVisible(true);
        this.context = context;
        this.createBufferStrategy(2);

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
                        Thread.sleep(10);
                        Hypothesis currentHypothesis = context.getAlgorithm().getBestHypothesis();
                        if (currentHypothesis != this.bestHypothesis) {
                            bestHypothesis = currentHypothesis;

                            BufferStrategy bf = App.this.getBufferStrategy();
                            Graphics g = bf.getDrawGraphics();
                            try {
                                context.getPainter().paint(g);
                            } finally {
                                g.dispose();
                            }
                            bf.show();
                            Toolkit.getDefaultToolkit().sync();
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
                        //Thread.sleep(3);
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
