package se.jtiden.ml.core.api;


import java.awt.*;

public class PointWithColor extends Point {
    public Color color;

    public PointWithColor(double x, double y, Color color) {
        super(x, y);
        this.color = color;
    }
}
