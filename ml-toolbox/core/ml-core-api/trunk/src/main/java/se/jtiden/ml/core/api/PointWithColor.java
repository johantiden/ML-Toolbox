package se.jtiden.ml.core.api;

public class PointWithColor extends Point {
    public JTColor color;

    public PointWithColor(double x, double y, JTColor color) {
        super(x, y);
        this.color = color;
    }
}
