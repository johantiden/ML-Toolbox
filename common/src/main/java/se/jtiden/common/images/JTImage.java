package se.jtiden.common.images;

import se.jtiden.common.images.awt.AwtImageConverter;

public interface JTImage {
    int getWidth();
    int getHeight();

    JTColor getColorAt(int x, int y);

    AwtImageConverter getImageConverter();

    void setPixel(int x, int y, JTColor pixel);

    JTGraphics getGraphics();

    JTImage copy();
}
