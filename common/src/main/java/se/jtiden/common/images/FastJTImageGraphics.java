package se.jtiden.common.images;

public class FastJTImageGraphics implements JTGraphics {
    private FastJTImage fastJTImage;

    public FastJTImageGraphics(FastJTImage fastJTImage) {
        this.fastJTImage = fastJTImage;
    }

    @Override
    public void drawCircle(CircleWithColor circle) {
        int left = Math.max(circle.left(), 0);
        int top = Math.max(circle.top(), 0);
        int right = (int) Math.min(left + circle.getRadius()*2 + 1, fastJTImage.getWidth() - 1);
        int bottom = (int) Math.min(top + circle.getRadius()*2 + 1, fastJTImage.getHeight() - 1);

        for (int y = top; y < bottom; ++y) {
            for (int x = left; x < right; ++x) {
                final int index = fastJTImage.getIndex(x, y);

                if (isInsideCircle(circle, x, y)) {
                    //char alpha = (char) ((Math.max(circle.color.a - Math.sqrt((x - circle.x) * (x - circle.x) +
                    //                                                                (y - circle.y) * (y - circle.y)) / circle.radius*200, 0)));

                    mixPixel(index, circle.getColor().getR(), circle.getColor().getG(), circle.getColor().getB(), circle.getColor().getA());
                }
            }
        }
    }

    @Override
    public void drawRadial(CircleWithColor circle) {
        int left = Math.max(circle.left(), 0);
        int top = Math.max(circle.top(), 0);
        int right = (int) Math.min(left + circle.getRadius()*2 + 1, fastJTImage.getWidth() - 1);
        int bottom = (int) Math.min(top + circle.getRadius()*2 + 1, fastJTImage.getHeight() - 1);

        for (int y = top; y < bottom; ++y) {
            for (int x = left; x < right; ++x) {
                int index = fastJTImage.getIndex(x, y);

                if (isInsideCircle(circle, x, y)) {
                    JTColor color = circle.getColor();
                    char alpha = (char) Math.max(color.getA() * Math.pow((circle.getRadius() - Math.sqrt((x - circle.getX()) * (x - circle.getX()) +
                            (y - circle.getY()) * (y - circle.getY()))) / circle.getRadius(), 2), 0);

                    mixPixel(index, color.getR(), color.getG(), color.getB(), alpha);
                }
            }
        }
    }

    private void mixPixel(int index, int r, int g, int b, int a) {
        if (a == 255) {
            fastJTImage.setPixel(index, r, g, b);
        } else {
            double alphaPercent = a / 255d;
            int rFromOld = (int) ((1 - alphaPercent) * fastJTImage.getR(index));
            int gFromOld = (int) ((1 - alphaPercent) * fastJTImage.getG(index));
            int bFromOld = (int) ((1 - alphaPercent) * fastJTImage.getB(index));

            int rFromNew = (int) (alphaPercent * r);
            int gFromNew = (int) (alphaPercent * g);
            int bFromNew = (int) (alphaPercent * b);

            int mixedR = rFromOld + rFromNew;
            int mixedG = gFromOld + gFromNew;
            int mixedB = bFromOld + bFromNew;


            fastJTImage.setPixel(index, mixedR, mixedG, mixedB);
        }
    }


    private void mixPixel(int index, JTColor color) {

    }

    private boolean isInsideCircle(CircleWithColor circle, int x, int y) {
        return ((x - circle.getX()) * (x - circle.getX()) +
                (y - circle.getY()) * (y - circle.getY()))
                < (circle.getRadius() * circle.getRadius());
    }
}
