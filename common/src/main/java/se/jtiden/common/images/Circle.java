package se.jtiden.common.images;

import se.jtiden.common.math.Point;

public interface Circle extends Point {

    double getRadius();

    int left();

    int top();

    void setRadius(double radius);
}
