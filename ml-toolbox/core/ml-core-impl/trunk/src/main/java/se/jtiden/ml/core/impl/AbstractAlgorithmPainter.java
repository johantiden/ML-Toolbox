package se.jtiden.ml.core.impl;

import se.jtiden.ml.core.api.AlgorithmStepPainter;

import java.awt.*;

public abstract class AbstractAlgorithmPainter implements AlgorithmStepPainter {

    protected AlgorithmStepPainter innerPainter;

    public AbstractAlgorithmPainter(AlgorithmStepPainter innerPainter) {
        this.innerPainter = innerPainter;
    }

    @Override
    public void paint(Graphics g) {

    }
}
