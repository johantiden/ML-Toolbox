package se.jtiden.ml.core.impl;

import se.jtiden.ml.core.api.CircleWithColor;
import se.jtiden.ml.core.api.JTColor;
import se.jtiden.ml.core.api.JTGraphics;
import se.jtiden.ml.core.api.JTImage;

import java.awt.*;
import java.awt.image.BufferedImage;


public class FastJTImage implements JTImage {

    private final int width;
    private final int height;
    private final char[] r;
    private final char[] g;
    private final char[] b;

    public FastJTImage(int width, int height) {
        this.width = width;
        this.height = height;
        this.r = new char[width * height];
        this.g = new char[width * height];
        this.b = new char[width * height];
    }

    public static FastJTImage fromImage(final BufferedImage realImage, final int downscale) {
        FastJTImage fastJTImage = new FastJTImage(
                realImage.getWidth(null) / downscale,
                realImage.getHeight(null) / downscale);

        for (int y = 0; y < fastJTImage.getHeight(); ++y) {
            for (int x = 0; x < fastJTImage.getWidth(); ++x) {
                int argb = realImage.getRGB(x*downscale, y*downscale);

                Color pixel = new Color(
                        (argb >> 16) & 0xff, //red
                        (argb >> 8) & 0xff, //green
                        (argb) & 0xff  //blue
                );

                fastJTImage.setPixel(x, y,
                        (char)pixel.getRed(),
                        (char)pixel.getGreen(),
                        (char)pixel.getBlue());
            }
        }
        return fastJTImage;
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
            throw new IllegalArgumentException("Trying to access out of bounds. height: "+height+" y:" +y);
        }

        if (x >= width) {
            throw new IllegalArgumentException("Trying to access out of bounds. width: "+width+" x:" +x);
        }
    }

    @Override
    public Image asImage() {

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();


        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                g.setColor(getColorAt(x, y).toAwtColor());
                g.fillRect(x, y, 1, 1);
            }
        }

        g.dispose();

        return image;
    }

    @Override
    public JTColor getColorAt(int x, int y) {
        final int index = getIndex(x, y);
        if (index >= r.length) {
            System.err.println("Index out of bounds! x:" + x + " y:"+y + " size: " + r.length + " index:" + index + ".");
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
            public void draw(CircleWithColor circle) {
                final int left = Math.max(circle.left(), 0);
                final int top = Math.max(circle.top(), 0);
                final int right = Math.min(left + circle.diameter()+1, width-1);
                final int bottom = Math.min(top + circle.diameter()+1, height-1);

                for (int y = top; y < bottom; ++y) {
                    for (int x = left; x < right; ++x) {
                        final int index = getIndex(x, y);

                        if (isInsideCircle(circle, y, x)) {
                            r[index] = circle.color.r;
                            g[index] = circle.color.g;
                            b[index] = circle.color.b;
                        }
                    }
                }
            }

            private boolean isInsideCircle(final CircleWithColor circle, final int y, final int x) {
                return ((x - circle.x) * (x - circle.x) +
                     (y - circle.y) * (y - circle.y))
                        < (circle.radius * circle.radius);
            }
        };
    }
}
