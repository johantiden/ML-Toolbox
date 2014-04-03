package se.jtiden.common.images;

public class JTColorImpl implements JTColor {
    public static final JTColor GRAY = new JTColorImpl(128, 128, 128);
    public static final JTColor BLACK = new JTColorImpl(0, 0, 0);

    public static final int COLOR_MAX_LIMIT = 255;
    public static final int COLOR_MIN_LIMIT = 0;

    private final int r;
    private final int g;
    private final int b;
    private final int a;

    public JTColorImpl(int r, int g, int b, int a) {
        verify(r, g, b, a);
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    private static void verify(int r, int g, int b, int a) {
        verify(a);
        verify(r);
        verify(g);
        verify(b);
    }

    private static void verify(int c) {
        if (c < COLOR_MIN_LIMIT || c > COLOR_MAX_LIMIT) {
            throw new IllegalStateException("Color value out of bounds: " + c);
        }
    }

    public JTColorImpl(int r, int g, int b) {
        this(r, g, b, COLOR_MAX_LIMIT);
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
