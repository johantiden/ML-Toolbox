package se.jtiden.common.images;

public class CircleWithColor extends PointWithColor {
    public double radius;

    public CircleWithColor(final double x, final double y, final JTColor color, final double radius) {
        super(x, y, color);
        this.radius = radius;
    }

    public int left() {
        return (int) (x - radius);
    }

    public int top() {
        return (int) (y - radius);
    }

    public int diameter() {
        return (int) (radius * 2);
    }
}