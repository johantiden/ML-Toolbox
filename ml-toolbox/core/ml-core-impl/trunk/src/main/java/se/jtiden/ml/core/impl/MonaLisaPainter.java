package se.jtiden.ml.core.impl;

import se.jtiden.ml.core.api.AlgorithmStepPainter;

import java.awt.*;

public class MonaLisaPainter extends AbstractAlgorithmPainter {
    private final Image monaLisa;

    public MonaLisaPainter(MonaLisa monaLisa, AlgorithmStepPainter innerPainter) {
        super(innerPainter);

        this.monaLisa = monaLisa.getMonaLisa();
    }

    @Override
    public void paint(Graphics g) {
        if (innerPainter != null) {
            innerPainter.paint(g);
        }

        g.drawImage(monaLisa, 0, 0, null);
    }

    @Override
    public int getWidth() {
        return innerPainter != null ?
                Math.max(innerPainter.getWidth(), monaLisa.getWidth(null)) :
                monaLisa.getWidth(null);
    }

    @Override
    public int getHeight() {
        return innerPainter != null ?
                Math.max(innerPainter.getHeight(), monaLisa.getHeight(null)) :
                monaLisa.getHeight(null);
    }
}
