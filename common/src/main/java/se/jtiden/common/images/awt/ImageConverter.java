package se.jtiden.common.images.awt;

import se.jtiden.common.images.FastJTImage;
import se.jtiden.common.images.JTImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public final class ImageConverter {

    private ImageConverter() {
    }

    public static JTImage loadImage(String pathAndFileName, double scaleDown) {
        return toFastJTImage(getRealImage(pathAndFileName), scaleDown);
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

    public static FastJTImage toFastJTImage(BufferedImage image, double downscale) {
        FastJTImage fastJTImage = new FastJTImage(
                (int) (image.getWidth(null) / downscale),
                (int) (image.getHeight(null) / downscale));

        for (int y = 0; y < fastJTImage.getHeight(); ++y) {
            for (int x = 0; x < fastJTImage.getWidth(); ++x) {
                int argb = image.getRGB((int) (x * downscale), (int) (y * downscale));

                Color pixel = new Color(
                        argb >> 16 & 0xff, //red
                        argb >> 8 & 0xff, //green
                        argb >> 0 & 0xff  //blue
                );

                fastJTImage.setPixel(x, y,
                        pixel.getRed(),
                        pixel.getGreen(),
                        pixel.getBlue());
            }
        }
        return fastJTImage;
    }

}
