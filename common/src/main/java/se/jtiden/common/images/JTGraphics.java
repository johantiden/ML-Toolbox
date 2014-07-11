package se.jtiden.common.images;

public interface JTGraphics {
    void fillCircle(CircleWithColor circle);

    void drawRadial(CircleWithColor circleWithColor);

    void fillAll(JTColor color);

    void drawCircle(CircleWithColorImpl circleWithColor, double penWidth);
}
