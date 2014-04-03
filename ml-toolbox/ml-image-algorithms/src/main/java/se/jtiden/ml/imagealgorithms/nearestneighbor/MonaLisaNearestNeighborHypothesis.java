package se.jtiden.ml.imagealgorithms.nearestneighbor;

import se.jtiden.common.images.JTColor;
import se.jtiden.common.images.PointWithColor;
import se.jtiden.ml.imagealgorithms.MonaLisa;
import se.jtiden.ml.imagealgorithms.algorithm.api.Hypothesis;

import java.util.ArrayList;
import java.util.List;

public class MonaLisaNearestNeighborHypothesis implements Hypothesis {

    private final List<PointWithColor> points;
    private final MonaLisa monaLisa;
    private Double lossCached;

    public MonaLisaNearestNeighborHypothesis(
            MonaLisa monaLisa,
            List<PointWithColor> points) {
        this.monaLisa = monaLisa;
        this.points = points;
    }

    @Override
    public double valueFunction() {
        return innerValueFunction();
    }

    @Override
    public Hypothesis copy() {
        return new MonaLisaNearestNeighborHypothesis(monaLisa, new ArrayList<PointWithColor>(points));
    }

    public double innerValueFunction() {
        if (lossCached == null) {
            calculateInnerLoss();
        }

        return lossCached;
    }


    private void calculateInnerLoss() {
        MonaLisaNearestNeighborHypothesisPainter painter = new MonaLisaNearestNeighborHypothesisPainter(this);

        double loss = 0;
        for (int y = 0; y < monaLisa.getHeight(); y += 2) {
            for (int x = 0; x < monaLisa.getWidth(); x += 2) {
                loss -= colorDifferenceSquare(
                        getMonaLisa().getColorAt(x, y),
                        painter.getColorAt(x, y));

            }
        }

        lossCached = loss;
    }

    private double colorDifferenceSquare(JTColor color1, JTColor color2) {
        double diff = color1.difference(color2);
        return diff * diff;
    }


    public List<PointWithColor> getPoints() {
        return new ArrayList<PointWithColor>(points);
    }

    @Override
    public int compareTo(Hypothesis o) {
        if (this == o) {
            return 0;
        }

        return valueFunction() > o.valueFunction() ? 1 : -1;
    }

    public MonaLisa getMonaLisa() {
        return monaLisa;
    }
}
