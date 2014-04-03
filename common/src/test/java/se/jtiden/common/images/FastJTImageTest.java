package se.jtiden.common.images;


import org.junit.Assert;
import org.junit.Test;
import se.jtiden.common.images.JTColor;

import static org.junit.Assert.assertEquals;

public class FastJTImageTest {

    @Test
    public void test() {
        FastJTImage image = new FastJTImage(2, 2);
        JTColor c = image.getColorAt(1, 1);
        Assert.assertEquals(JTColor.BLACK, c);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOutsideY() {
        FastJTImage image = new FastJTImage(2, 2);
        JTColor c = image.getColorAt(1, 2);
        Assert.assertEquals(JTColor.BLACK, c);
    }
}
