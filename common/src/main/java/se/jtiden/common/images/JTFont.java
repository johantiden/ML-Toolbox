package se.jtiden.common.images;


import java.awt.*;

public class JTFont {
    public static final JTFont ARIAL_15 = new JTFont("arial", JTColor.BLACK, 15);

    private final Font font;
    private Color color;

    public JTFont(String name, JTColor color, int size) {
        this.font = new Font(name, Font.PLAIN, size);
        this.color = color.asAwtColor();
    }

    public Font getFont() {
        return font;
    }

    public Color getColor() {
        return color;
    }
}



