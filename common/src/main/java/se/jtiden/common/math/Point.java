package se.jtiden.common.math;


public class Point implements Comparable<Point> {
    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public int xInt() {
        return (int) Math.round(x);
    }

    public int yInt() {
        return (int) Math.round(y);
    }

    public double distanceSquaredFrom(double x, double y) {
        double dx = (this.x - x);
        double dy = (this.y - y);
        double distance = dx * dx + dy * dy;
        //if (x < 0 || y < 0 || this.x < 0 || this.y < 0) {
        //System.out.println("This: ("+this.x+","+this.y+")"+ " other: ("+x+","+y+") distance:" + distance);
        //}
        return distance;
    }

    @Override
    public int compareTo(Point o) {
        if (this == o) {
            return 0;
        }

        if (this.x < o.x) {
            return -1;
        }

        if (this.y < o.y) {
            return -1;
        }

        return 1;
    }
}
