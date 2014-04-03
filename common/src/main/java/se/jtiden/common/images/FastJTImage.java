package se.jtiden.common.images;

import java.util.Arrays;

public class FastJTImage implements JTImage {

    private final int width;
    private final int height;
    private final char[] r;
    private final char[] g;
    private final char[] b;

    public FastJTImage(int width, int height) {
        this(
                width, height, new char[width * height],
                new char[width * height],
                new char[width * height]);
    }

    public FastJTImage(int width, int height, char[] r, char[] g, char[] b) {
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


    private int getIndex(int x, int y) {
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
        return new JTColor(
                r[index],
                g[index],
                b[index]);
    }

    @Override
    public void setPixel(int x, int y, JTColor color) {
        setPixel(
                x,
                y,
                color.r,
                color.g,
                color.b);
    }

    public void setPixel(int x, int y, char r, char g, char b) {
        final int index = getIndex(x, y);
        this.r[index] = r;
        this.g[index] = g;
        this.b[index] = b;
    }

    @Override
    public JTGraphics getGraphics() {
        return new JTGraphics() {
            @Override
            public void drawCircle(CircleWithColor circle) {
                final int left = Math.max(circle.left(), 0);
                final int top = Math.max(circle.top(), 0);
                final int right = Math.min(left + circle.diameter() + 1, width - 1);
                final int bottom = Math.min(top + circle.diameter() + 1, height - 1);

                for (int y = top; y < bottom; ++y) {
                    for (int x = left; x < right; ++x) {
                        final int index = getIndex(x, y);

                        if (isInsideCircle(circle, x, y)) {
                            //char alpha = (char) ((Math.max(circle.color.a - Math.sqrt((x - circle.x) * (x - circle.x) +
                            //                                                                (y - circle.y) * (y - circle.y)) / circle.radius*200, 0)));

                            mixPixel(index, circle.color.r, circle.color.g, circle.color.b, circle.color.a);
                        }
                    }
                }
            }

            @Override
            public void drawRadial(CircleWithColor circle) {
                final int left = Math.max(circle.left(), 0);
                final int top = Math.max(circle.top(), 0);
                final int right = Math.min(left + circle.diameter() + 1, width - 1);
                final int bottom = Math.min(top + circle.diameter() + 1, height - 1);

                for (int y = top; y < bottom; ++y) {
                    for (int x = left; x < right; ++x) {
                        final int index = getIndex(x, y);

                        if (isInsideCircle(circle, x, y)) {
                            char alpha = (char) ((Math.max(circle.color.a * Math.pow((circle.radius - Math.sqrt((x - circle.x) * (x - circle.x) +
                                    (y - circle.y) * (y - circle.y))) / circle.radius, 2), 0)));

                            mixPixel(index, circle.color.r, circle.color.g, circle.color.b, alpha);
                        }
                    }
                }
            }

            private void mixPixel(int index, char r, char g, char b, char a) {
                if (a == 255) {
                    FastJTImage.this.r[index] = r;
                    FastJTImage.this.g[index] = g;
                    FastJTImage.this.b[index] = b;
                } else {
                    double alphaPercent = a / 255d;
                    char rFromOld = (char) ((1 - alphaPercent) * FastJTImage.this.r[index]);
                    char gFromOld = (char) ((1 - alphaPercent) * FastJTImage.this.g[index]);
                    char bFromOld = (char) ((1 - alphaPercent) * FastJTImage.this.b[index]);

                    char rFromNew = (char) (alphaPercent * r);
                    char gFromNew = (char) (alphaPercent * g);
                    char bFromNew = (char) (alphaPercent * b);

                    char mixedR = (char) (rFromOld + rFromNew);
                    char mixedG = (char) (gFromOld + gFromNew);
                    char mixedB = (char) (bFromOld + bFromNew);

                    FastJTImage.this.r[index] = mixedR;
                    FastJTImage.this.g[index] = mixedG;
                    FastJTImage.this.b[index] = mixedB;
                }
            }


            private void mixPixel(int index, JTColor color) {

            }

            private boolean isInsideCircle(final CircleWithColor circle, final int x, final int y) {
                return ((x - circle.x) * (x - circle.x) +
                        (y - circle.y) * (y - circle.y))
                        < (circle.radius * circle.radius);
            }
        };
    }

    @Override
    public JTImage copy() {
        return new FastJTImage(width, height, copy(r), copy(g), copy(b));
    }

    private char[] copy(char[] array) {
        return Arrays.copyOf(array, array.length);
    }
}
