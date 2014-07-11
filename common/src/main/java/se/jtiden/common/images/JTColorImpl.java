package se.jtiden.common.images;

import java.awt.*;
import java.util.Random;

public class JTColorImpl implements JTColor {

    public static final int COLOR_MAX_LIMIT = 255;
    public static final int COLOR_MIN_LIMIT = 0;

    private final int r;
    private final int g;
    private final int b;
    private final int a;
    private final Random random = new Random();

    public JTColorImpl(int r, int g, int b, int a) {
        verify(r, g, b, a);
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public JTColorImpl(JTColor baseColor, int a) {
        this(baseColor.getR(), baseColor.getG(), baseColor.getB(), a);
    }

    private static void verify(int r, int g, int b, int a) {
        verify(a, 'a');
        verify(r, 'r');
        verify(g, 'g');
        verify(b, 'b');
    }

    private static void verify(int c, char channel) {
        if (c < COLOR_MIN_LIMIT || c > COLOR_MAX_LIMIT) {
            throw new IllegalStateException("Color '"+channel+"' value out of bounds: " + c);
        }
    }

    public JTColorImpl(int r, int g, int b) {
        this(r, g, b, COLOR_MAX_LIMIT);
    }

    @Override
    public JTColor randomizeColor(int colorVariance) {
        return new JTColorImpl(
                Math.max(0, Math.min(r + random.nextInt(colorVariance) - colorVariance / 2, a)),
                Math.max(0, Math.min(g + random.nextInt(colorVariance) - colorVariance / 2, a)),
                Math.max(0, Math.min(b + random.nextInt(colorVariance) - colorVariance / 2, a)));
    }

    @Override
    public Color asAwtColor() {
        return new Color(r, g, b, a);
    }

    @Override
    public int difference(JTColor color2) {
        int rDiff = Math.abs(r - color2.getR());
        int gDiff = Math.abs(g - color2.getG());
        int bDiff = Math.abs(b - color2.getB());
        return rDiff + gDiff + bDiff;
    }

    @Override
    public int getR() {
        return r;
    }

    @Override
    public int getG() {
        return g;
    }

    @Override
    public int getB() {
        return b;
    }

    @Override
    public int getA() {
        return a;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JTColor)) return false;

        JTColor jtColor = (JTColor) o;

        if (b != jtColor.getB()) return false;
        if (g != jtColor.getG()) return false;
        if (r != jtColor.getR()) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = r;
        result = 31 * result + g;
        result = 31 * result + b;
        return result;
    }
}
