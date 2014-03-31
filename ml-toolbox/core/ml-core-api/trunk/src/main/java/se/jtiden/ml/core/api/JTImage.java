package se.jtiden.ml.core.api;

import java.awt.*;

public interface JTImage {
    int getWidth();
    int getHeight();

    JTColor getColorAt(int x, int y);

    Image asImage();

    void setPixel(int x, int y, JTColor pixel);

    JTGraphics getGraphics();

    JTImage copy();
}
