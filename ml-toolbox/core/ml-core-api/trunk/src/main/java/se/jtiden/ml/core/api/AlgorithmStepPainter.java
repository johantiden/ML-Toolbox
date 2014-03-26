package se.jtiden.ml.core.api;

import java.awt.*;

public interface AlgorithmStepPainter {
    void paint(Graphics g);

    int getWidth();
    int getHeight();
}
