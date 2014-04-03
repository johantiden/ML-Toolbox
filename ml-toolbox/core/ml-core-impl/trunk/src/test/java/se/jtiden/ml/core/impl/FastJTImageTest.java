package se.jtiden.ml.core.impl;


import org.junit.Test;
import se.jtiden.common.images.FastJTImage;
import se.jtiden.common.images.JTColor;

import static org.junit.Assert.assertEquals;

public class FastJTImageTest {

    @Test
    public void test() {
        FastJTImage image = new FastJTImage(2, 2);
        JTColor c = image.getColorAt(1, 1);
        assertEquals(JTColor.BLACK, c);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOutsideY() {
        FastJTImage image = new FastJTImage(2, 2);
        JTColor c = image.getColorAt(1, 2);
        assertEquals(JTColor.BLACK, c);
    }
}
