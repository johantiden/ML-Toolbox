package se.jtiden.common.images;

public interface JTColor {
    int getR();
    int getG();
    int getB();
    int getA();

    int difference(JTColor color2);
}
