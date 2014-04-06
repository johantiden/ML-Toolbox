package se.jtiden.ml.imagealgorithms.nearestneighbor;

import se.jtiden.common.images.JTColor;
import se.jtiden.common.images.JTImage;
import se.jtiden.common.images.PointWithColor;
import se.jtiden.ml.imagealgorithms.algorithm.api.Hypothesis;

import java.util.ArrayList;
import java.util.List;

public class MonaLisaNearestNeighborHypothesis implements Hypothesis {

    private final List<PointWithColor> points;
    private final JTImage targetImage;
    private Double lossCached;

    public MonaLisaNearestNeighborHypothesis(
            JTImage targetImage,
            List<PointWithColor> points) {
        this.targetImage = targetImage;
        this.points = points;
    }

    @Override
    public double valueFunction() {
        return innerValueFunction();
    }

    @Override
    public Hypothesis copy() {
        return new MonaLisaNearestNeighborHypothesis(targetImage, new ArrayList<PointWithColor>(points));
    }

    public double innerValueFunction() {
        if (lossCached == null) {
            lossCached = calculateInnerLoss();
        }

        return lossCached;
    }


    private double calculateInnerLoss() {
        MonaLisaNearestNeighborHypothesisPainter painter = new MonaLisaNearestNeighborHypothesisPainter(this);

        double loss = 0;
        for (int y = 0; y < targetImage.getHeight(); y += 2) {
            for (int x = 0; x < targetImage.getWidth(); x += 2) {
                loss -= colorDifferenceSquare(
                        getTargetImage().getColorAt(x, y),
                        painter.getColorAt(x, y));

            }
        }

        return loss;
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

    public JTImage getTargetImage() {
        return targetImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MonaLisaNearestNeighborHypothesis)) return false;

        MonaLisaNearestNeighborHypothesis that = (MonaLisaNearestNeighborHypothesis) o;

        if (lossCached != null ? !lossCached.equals(that.lossCached) : that.lossCached != null) return false;
        if (!points.equals(that.points)) return false;
        if (!targetImage.equals(that.targetImage)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = points.hashCode();
        result = 31 * result + targetImage.hashCode();
        result = 31 * result + (lossCached != null ? lossCached.hashCode() : 0);
        return result;
    }
}
