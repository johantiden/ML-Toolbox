package se.jtiden.ml.core.api;

import java.awt.*;

public class JTColor {
    public static final JTColor GRAY = new JTColor(128, 128, 128);
    public static final JTColor BLACK = new JTColor(0, 0, 0);
    public static final JTColor WHITE = new JTColor(255, 255, 255);

    public char r;

    public char g;
    public char b;
    public JTColor(char r, char g, char b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public JTColor(int r, int g, int b) {
        this((char)r, (char)b, (char)g);
    }

    public static int difference(JTColor color1, JTColor color2) {
        int rDiff = Math.abs(color1.r - color2.r);
        int gDiff = Math.abs(color1.g - color2.g);
        int bDiff = Math.abs(color1.b - color2.b);
        return rDiff + gDiff + bDiff;
    }


    public Color toAwtColor() {
        return new Color(r, g, b);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof JTColor)) return false;

        final JTColor jtColor = (JTColor) o;

        if (b != jtColor.b) return false;
        if (g != jtColor.g) return false;
        if (r != jtColor.r) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) r;
        result = 31 * result + (int) g;
        result = 31 * result + (int) b;
        return result;
    }
}
