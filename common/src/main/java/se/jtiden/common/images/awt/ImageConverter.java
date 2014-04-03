package se.jtiden.common.images.awt;

import se.jtiden.common.images.FastJTImage;
import se.jtiden.common.images.JTImage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageConverter {

    public static BufferedImage toAwtImage(JTImage image) {
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

        Graphics g = bufferedImage.getGraphics();

        for (int y = 0; y < image.getHeight(); ++y) {
            for (int x = 0; x < image.getWidth(); ++x) {
                g.setColor(new AwtColor(image.getColorAt(x, y)));
                g.fillRect(x, y, 1, 1);
            }
        }

        g.dispose();

        return bufferedImage;
    }

    public static FastJTImage toFastJTImage(BufferedImage image, final double downscale) {
        FastJTImage fastJTImage = new FastJTImage(
                (int) (image.getWidth(null) / downscale),
                (int) (image.getHeight(null) / downscale));

        for (int y = 0; y < fastJTImage.getHeight(); ++y) {
            for (int x = 0; x < fastJTImage.getWidth(); ++x) {
                int argb = image.getRGB((int) (x * downscale), (int) (y * downscale));

                Color pixel = new Color(
                        (argb >> 16) & 0xff, //red
                        (argb >> 0) & 0xff, //green
                        (argb >> 8) & 0xff  //blue
                );

                fastJTImage.setPixel(x, y,
                        (char) pixel.getRed(),
                        (char) pixel.getGreen(),
                        (char) pixel.getBlue());
            }
        }
        return fastJTImage;
    }

}
