package se.jtiden.ml.imagealgorithms.painter;

import java.awt.*;

public interface AlgorithmStepPainter {
    void paint();
    Image getImage();

    int getWidth();
    int getHeight();

}
