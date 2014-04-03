package se.jtiden.common.images;

import se.jtiden.common.math.PointImpl;

public class PointWithColor extends PointImpl implements Colorful {
    private JTColor color;

    public PointWithColor(double x, double y, JTColor color) {
        super(x, y);
        this.color = color;
    }

    @Override
    public void setColor(JTColor color) {
        this.color = color;
    }

    @Override
    public JTColor getColor() {
        return color;
    }

}
