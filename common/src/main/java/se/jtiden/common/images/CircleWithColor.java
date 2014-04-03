package se.jtiden.common.images;

public class CircleWithColor extends PointWithColor implements Circle {
    private double radius;

    public CircleWithColor(double x, double y, JTColor color, double radius) {
        super(x, y, color);
        this.radius = radius;
    }

    @Override
    public int left() {
        return (int) (x - radius);
    }

    @Override
    public int top() {
        return (int) (y - radius);
    }

    public int diameterInt() {
        return (int) (radius * 2);
    }

    @Override
    public double getRadius() {
        return radius;
    }

    @Override
    public void setRadius(double radius) {
        this.radius = radius;
    }
}
