package se.jtiden.common.images;

import se.jtiden.common.math.Point;

import java.awt.*;

public class StringToDrawQueued {
    private final Point center;
    private final JTFont font;
    private final String text;

    public StringToDrawQueued(Point center, JTFont font, String text) {
        this.center = center;
        this.font = font;
        this.text = text;
    }

    public void draw(Graphics2D g) {
        g.setFont(font.getFont());
        g.setColor(font.getColor());
    }
}
