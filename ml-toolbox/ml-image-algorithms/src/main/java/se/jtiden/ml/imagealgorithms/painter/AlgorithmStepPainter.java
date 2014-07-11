package se.jtiden.ml.imagealgorithms.painter;


import se.jtiden.common.images.JTImage;

import java.io.Serializable;

public interface AlgorithmStepPainter extends Serializable {
    void paint();

    JTImage getImage();

    int getWidth();

    int getHeight();

}
