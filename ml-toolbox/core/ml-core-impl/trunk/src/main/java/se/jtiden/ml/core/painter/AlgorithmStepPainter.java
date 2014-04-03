package se.jtiden.ml.core.painter;

import java.awt.*;

public interface AlgorithmStepPainter {
    void paint();
    Image getImage();

    int getWidth();
    int getHeight();

}
