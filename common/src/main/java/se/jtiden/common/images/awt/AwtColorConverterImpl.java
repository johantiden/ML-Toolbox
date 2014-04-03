package se.jtiden.common.images.awt;

import java.awt.*;
import java.awt.image.BufferedImage;

import se.jtiden.common.images.JTImage;

public class AwtColorConverterImpl implements AwtImageConverter {
    private JTImage image;

    public AwtColorConverterImpl(final JTImage image) {
        this.image = image;
    }

    @Override
    public Image getAwtImage() {
        BufferedImage awtImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

        Graphics g = awtImage.getGraphics();

        for (int y = 0; y < image.getHeight(); ++y) {
            for (int x = 0; x < image.getWidth(); ++x) {
                g.setColor(image.getColorAt(x, y).toAwtColor());
                g.fillRect(x, y, 1, 1);
            }
        }

        g.dispose();

        return awtImage;
    }
}
