package se.jtiden.common.images;

public interface JTImage {
    int getWidth();

    int getHeight();

    JTColor getColorAt(int x, int y);

    void setPixel(int x, int y, JTColor pixel);

    JTGraphics getGraphics();

    JTImage copy();
}
