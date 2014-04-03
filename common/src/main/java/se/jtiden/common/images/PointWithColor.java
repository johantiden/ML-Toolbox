package se.jtiden.common.images;

import se.jtiden.common.images.JTColor;
import se.jtiden.common.math.Point;

public class PointWithColor extends Point {
    public JTColor color;

    public PointWithColor(double x, double y, JTColor color) {
        super(x, y);
        this.color = color;
    }
}
