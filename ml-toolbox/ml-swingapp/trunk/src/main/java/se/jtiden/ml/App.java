package se.jtiden.ml;

import se.jtiden.ml.core.api.Context;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame {
    private Context context;

    protected App(Context context) {
        setSize(context.getPainter().getWidth(),
                context.getPainter().getHeight());

        setVisible(true);
        this.context = context;

        startSimulating();
        startPainting();
    }

    private void startPainting() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                        context.getPainter().paint(graphics());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private Graphics graphics() {
        return getGraphics();
    }

    private void startSimulating() {
        new Thread(new Runnable() {
            public long step;

            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(3);
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
