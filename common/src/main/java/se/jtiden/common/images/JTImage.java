package se.jtiden.common.images;

public interface JTImage {
    int getWidth();

    int getHeight();

    JTColor getColorAt(int x, int y);

    JTGraphics getGraphics();

    JTImage copy();

    void setPixel(int x, int y, JTColor pixel);

    void setPixel(int x, int y, int r, int g, int b);
}
