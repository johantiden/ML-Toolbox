package se.jtiden.ml.imagealgorithms;

import se.jtiden.common.images.JTColor;
import se.jtiden.common.images.JTImage;
import se.jtiden.common.images.awt.ImageConverter;
import se.jtiden.common.math.Point;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class MonaLisa {

    public static final String RGB = "rgb.gif";
    public static final String MONALISA = "monalisa.jpg";
    private double downScale;
    private JTImage monaLisa;

    public MonaLisa(double downScale) {
        this.downScale = downScale;
//      monaLisa = getImage("monalisa3.jpg");
//      monaLisa = getImage("stranden.jpg");
        monaLisa = getImage(MONALISA);
//        monaLisa = getImage("japan.jpg");
//        monaLisa = getImage(RGB);
    }

    private JTImage getImage(String pathAndFileName) {
        return ImageConverter.toFastJTImage(getRealImage(pathAndFileName), downScale);
    }

    private static BufferedImage getRealImage(String pathAndFileName) {
        try {
            URL url = Thread.currentThread().getContextClassLoader().getResource(pathAndFileName);
            return ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JTImage getImage() {
        return monaLisa;
    }

    public JTColor getColorAt(int x, int y) {
        return monaLisa.getColorAt(x, y);

    }

    public int getWidth() {
        return monaLisa.getWidth();
    }

    public int getHeight() {
        return monaLisa.getHeight();
    }

    public JTColor getColorAt(Point p) {
        return getColorAt(p.xInt(), p.yInt());
    }

    @Override
    public String toString() {
        return "MonaLisa{" +
                "downScale=" + downScale +
                ", monaLisa=" + monaLisa +
                '}';
    }
}
