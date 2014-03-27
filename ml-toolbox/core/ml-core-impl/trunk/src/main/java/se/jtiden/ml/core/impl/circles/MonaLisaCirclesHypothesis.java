package se.jtiden.ml.core.impl.circles;

import java.awt.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import se.jtiden.ml.core.api.CircleWithColor;
import se.jtiden.ml.core.api.Hypothesis;
import se.jtiden.ml.core.impl.MonaLisa;

public class MonaLisaCirclesHypothesis implements Hypothesis {

    private final List<CircleWithColor> circles;
    private final MonaLisa monaLisa;
    private BigInteger lossCached;

    public MonaLisaCirclesHypothesis(
            MonaLisa monaLisa,
            List<CircleWithColor> points) {
        this.monaLisa = monaLisa;
        this.circles = points;
    }

    @Override
    public BigInteger valueFunction() {
        return innerValueFunction();

        //if (parent == null) {
        //    return innerValueFunction();
        //}
        //return innerValueFunction() - parent.valueFunction();
    }

    public BigInteger innerValueFunction() {
        if (lossCached == null) {
            calculateInnerLoss();
        }

        return lossCached;
    }

    private static final Random random = new Random();

    private void calculateInnerLoss() {
        MonaLisaCirclesHypothesisPainter painter = new MonaLisaCirclesHypothesisPainter(this, null, 255, 0, monaLisa.getWidth(), monaLisa.getHeight());

        BigInteger loss = BigInteger.ZERO;
        for (int y = 0; y < monaLisa.getHeight(); y += 2) {
            for (int x = 0; x < monaLisa.getWidth(); x += 2) {
//        for(int i = 0; i < 500; ++i) {
//                int x = random.nextInt(monaLisa.getWidth());
//                int y = random.nextInt(monaLisa.getHeight());
                Color estimate = painter.colorAt(x, y);
                if (false /*isBlack(estimate)*/) {
                    loss = loss.subtract(BigInteger.valueOf(589824*3));
                } else {
                    loss = loss.subtract(colorDifferenceSquare(
                            getMonaLisa().getColorAt(x, y),
                            estimate));
                }

            }
        }

        lossCached = loss;
    }

    private boolean isBlack(final Color color) {
        return color.getRed() == 0 &&
                color.getGreen() == 0 &&
                color.getBlue() == 0;
    }

    private BigInteger colorDifferenceSquare(Color color1, Color color2) {
        return colorDifference(color1, color2).pow(2);
    }

    private BigInteger colorDifference(final Color color1, final Color color2) {
        int rDiff = Math.abs(color1.getRed() - color2.getRed());
        int gDiff = Math.abs(color1.getGreen() - color2.getGreen());
        int bDiff = Math.abs(color1.getBlue() - color2.getBlue());

        return BigInteger.valueOf(rDiff + gDiff + bDiff);
    }


    public List<CircleWithColor> getCircles() {
        return new ArrayList<CircleWithColor>(circles);
    }

    @Override
    public int compareTo(Hypothesis o) {
        if (this == o) {
            return 0;
        }

        return valueFunction().compareTo(o.valueFunction());
    }

    public MonaLisa getMonaLisa() {
        return monaLisa;
    }

    public int countCircles() {
        return circles.size();
    }
}
