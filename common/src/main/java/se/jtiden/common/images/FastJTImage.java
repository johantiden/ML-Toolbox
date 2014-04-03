package se.jtiden.common.images;

import java.util.Arrays;

public class FastJTImage implements JTImage {

    private final int width;
    private final int height;
    private final int[] r;
    private final int[] g;
    private final int[] b;

    public FastJTImage(int width, int height) {
        this(
                width, height, new int[width * height],
                new int[width * height],
                new int[width * height]);
    }

    public FastJTImage(int width, int height, int[] r, int[] g, int[] b) {
        this.width = width;
        this.height = height;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }


    int getIndex(int x, int y) {
        verify(x, y);
        return y * width + x;
    }

    private void verify(int x, int y) {
        if (y >= height) {
            throw new IllegalArgumentException("Trying to access out of bounds. height: " + height + " y:" + y);
        }

        if (x >= width) {
            throw new IllegalArgumentException("Trying to access out of bounds. width: " + width + " x:" + x);
        }
    }


    @Override
    public JTColor getColorAt(int x, int y) {
        final int index = getIndex(x, y);
        if (index >= r.length) {
            System.err.println("Index out of bounds! x:" + x + " y:" + y + " size: " + r.length + " index:" + index + ".");
            return null;
        }
        return new JTColorImpl(
                r[index],
                g[index],
                b[index]);
    }

    @Override
    public void setPixel(int x, int y, JTColor color) {
        setPixel(
                x,
                y,
                color.getR(),
                color.getG(),
                color.getB());
    }

    @Override
    public void setPixel(int x, int y, int r, int g, int b) {
        int index = getIndex(x, y);
        setPixel(index, r, g, b);
    }

    void setPixel(int index, int r, int g, int b) {
        this.r[index] = r;
        this.g[index] = g;
        this.b[index] = b;
    }

    @Override
    public JTGraphics getGraphics() {
        return new FastJTImageGraphics(this);
    }

    @Override
    public JTImage copy() {
        return new FastJTImage(width, height, copy(r), copy(g), copy(b));
    }

    private static int[] copy(int[] array) {
        return Arrays.copyOf(array, array.length);
    }

    public int getR(int index) {
        return r[index];
    }

    public int getG(int index) {
        return g[index];
    }

    public int getB(int index) {
        return b[index];
    }
}
