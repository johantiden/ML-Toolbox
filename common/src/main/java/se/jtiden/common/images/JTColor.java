package se.jtiden.common.images;

import java.awt.*;

public interface JTColor {
    JTColor YELLOW = new JTColorImpl(255, 255, 0);
    JTColor RED = new JTColorImpl(255, 0, 0);
    JTColor GREEN = new JTColorImpl(0, 255, 0);
    JTColor BLUE = new JTColorImpl(0, 0, 255);

    JTColor WHITE = new JTColorImpl(255, 255, 255);
    JTColor GRAY = new JTColorImpl(128, 128, 128);
    JTColor BLACK = new JTColorImpl(0, 0, 0);

    int getR();
    int getG();
    int getB();
    int getA();

    int difference(JTColor color2);

    JTColor randomizeColor(int colorVariance);

    Color asAwtColor();
}
