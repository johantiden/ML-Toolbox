package se.jtiden.ml.core.api;

import java.awt.*;

public interface AlgorithmStepPainter {
    void paint();
    Image getImage();

    int getWidth();
    int getHeight();

}
