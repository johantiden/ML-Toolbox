package se.jtiden.common.images.awt;

import se.jtiden.common.images.JTColor;

import java.awt.*;

public class AwtColor extends Color {
    private static final long serialVersionUID = 1167621373558881766L;

    public AwtColor(JTColor color) {
        super(color.getR(), color.getG(), color.getB(), color.getA());
    }
}
