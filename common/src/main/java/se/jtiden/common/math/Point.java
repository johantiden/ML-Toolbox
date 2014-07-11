package se.jtiden.common.math;

public interface Point extends Comparable<Point> {
    int xInt();

    int yInt();

    double distanceSquaredFrom(double x, double y);

    double distanceFrom(double x, double y);

    @Override
    int compareTo(Point o);

    @Override
    String toString();

    double getX();

    double getY();

}
