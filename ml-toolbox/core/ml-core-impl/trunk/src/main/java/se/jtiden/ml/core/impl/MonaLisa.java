package se.jtiden.ml.core.impl;

import se.jtiden.ml.core.api.*;
import se.jtiden.ml.core.api.Point;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class MonaLisa {
    private BufferedImage monaLisa;

    public MonaLisa() {
        monaLisa = getImage("monalisa3.jpg");
    }

    public static BufferedImage getImage(final String pathAndFileName) {
        try {
            final URL url = Thread.currentThread().getContextClassLoader().getResource(pathAndFileName);
            return ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Image getMonaLisa() {
        return monaLisa;
    }

    public Color getColorAt(int x, int y) {
        //System.out.println("w:" + monaLisa.getWidth() + ", h" + monaLisa.getHeight() + " (" + x + "," +y+")");

        int argb = monaLisa.getRGB(x, y);

        return new Color(
                (argb >> 16) & 0xff, //red
                (argb >> 8) & 0xff, //green
                (argb) & 0xff  //blue
        );
    }

    public int getWidth() {
        return monaLisa.getWidth(null);
    }

    public int getHeight() {
        return monaLisa.getHeight(null);
    }

    public Color getColorAt(Point p) {
        return getColorAt(p.xInt(), p.yInt());
    }
}
