package se.jtiden.ml.imagealgorithms.evaluator;

import se.jtiden.common.images.JTColor;
import se.jtiden.common.images.JTImage;

public class DifferenceSquaredEvaluator implements Evaluator<JTImage> {

    private final JTImage targetImage;

    public DifferenceSquaredEvaluator(JTImage targetImage) {
        this.targetImage = targetImage;
    }

    @Override
    public double getScore(JTImage image) {
        double loss = 0;
        for (int y = 0; y < targetImage.getHeight(); ++y) {
            for (int x = 0; x < targetImage.getWidth(); ++x) {
//        for(int i = 0; i < 500; ++i) {
//                int x = random.nextInt(monaLisa.getWidth());
//                int y = random.nextInt(monaLisa.getHeight());
                loss -= colorDifferenceSquare(
                        targetImage.getColorAt(x, y),
                        image.getColorAt(x, y));

            }
        }

        return loss;
    }

    @Override
    public JTImage getTarget() {
        return targetImage;
    }

    private static double colorDifferenceSquare(JTColor color1, JTColor color2) {
        double diff = color1.difference(color2);
        return diff * diff;
    }

}
