package se.jtiden.common.images.awt;

import se.jtiden.common.images.JTColor;

import java.awt.*;

public class AwtColor extends Color {
    public AwtColor(JTColor color) {
        super(color.r, color.g, color.b, color.a);
    }
}
