package se.jtiden.ml.core.impl.nearestneighbor;

import se.jtiden.ml.core.api.Hypothesis;
import se.jtiden.ml.core.api.PointWithColor;
import se.jtiden.ml.core.impl.MonaLisa;

import java.awt.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MonaLisaNearestNeighborHypothesis implements Hypothesis {

    private final List<PointWithColor> points;
    private final MonaLisa monaLisa;
    private int numNeighborsForClassification;
    private BigInteger lossCached;

    public MonaLisaNearestNeighborHypothesis(
            MonaLisa monaLisa,
            List<PointWithColor> points,
            int numNeighborsForClassification) {
        this.monaLisa = monaLisa;
        this.points = points;
        this.numNeighborsForClassification = numNeighborsForClassification;
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
        MonaLisaNearestNeighborHypothesisPainter painter = new MonaLisaNearestNeighborHypothesisPainter(this, null, 255, 0);

        BigInteger loss = BigInteger.ZERO;
//        for (int y = 0; y < monaLisa.getHeight(); y += 2) {
//            for (int x = 0; x < monaLisa.getWidth(); x += 2) {
        for(int i = 0; i < 500; ++i) {
                int x = random.nextInt(monaLisa.getWidth());
                int y = random.nextInt(monaLisa.getHeight());
                loss = loss.subtract(colorDifferenceSquare(
                        getMonaLisa().getColorAt(x, y),
                        painter.colorAt(x, y)));

//            }
        }

        lossCached = loss;
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


    public List<PointWithColor> getPoints() {
        return new ArrayList<PointWithColor>(points);
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

    public int getNumNeighborsForClassification() {
        return numNeighborsForClassification;
    }
}
