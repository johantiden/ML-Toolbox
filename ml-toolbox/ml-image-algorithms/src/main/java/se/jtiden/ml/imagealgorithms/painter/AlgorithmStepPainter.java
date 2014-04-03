package se.jtiden.ml.imagealgorithms.painter;


import se.jtiden.common.images.JTImage;

public interface AlgorithmStepPainter {
    void paint();

    JTImage getImage();

    int getWidth();

    int getHeight();

}
