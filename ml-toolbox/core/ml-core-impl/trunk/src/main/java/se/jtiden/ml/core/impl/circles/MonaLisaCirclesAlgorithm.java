package se.jtiden.ml.core.impl.circles;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import se.jtiden.ml.core.api.CircleWithColor;
import se.jtiden.ml.core.api.IterativeAlgorithm;
import se.jtiden.ml.core.api.Point;
import se.jtiden.ml.core.impl.MonaLisa;

public class MonaLisaCirclesAlgorithm implements IterativeAlgorithm<MonaLisaCirclesHypothesis> {

    //private final SortedSet<MonaLisaNearestNeighborHypothesis> hypotheses;
    private final double mutationPointSpaceVariance;
    private final static Random random = new Random();
    private final double chanceToMutatePoint;
    private final int mutationPointColorVariance;
    private MonaLisaCirclesHypothesis bestHypothesis;
    private final double radiusVariance;

    public MonaLisaCirclesAlgorithm(
            MonaLisa monaLisa,
            int numHypotheses,
            int numPoints,
            double mutationPointVariance,
            double chanceToMutatePoint, int mutationPointColorVariance, final double radiusVariance) {
        this.mutationPointColorVariance = mutationPointColorVariance;
        //this.hypotheses = new TreeSet<MonaLisaNearestNeighborHypothesis>();
        this.mutationPointSpaceVariance = mutationPointVariance;
        this.chanceToMutatePoint = chanceToMutatePoint;
        this.radiusVariance = radiusVariance;
        createRandomInitialHypotheses(monaLisa, numHypotheses, numPoints);
    }

    private void createRandomInitialHypotheses(MonaLisa monaLisa, int numHypotheses, int numPoints) {
         bestHypothesis = randomHypothesis(monaLisa, numPoints);
            //for (int i = 0; i < numHypotheses; ++i) {
            //    hypotheses.add(randomHypothesis(monaLisa, numPoints));
            //}

    }

    private MonaLisaCirclesHypothesis randomHypothesis(MonaLisa monaLisa, int numPoints) {

        CircleWithColor middleOfImage = new CircleWithColor(
                monaLisa.getWidth() / 2d,
                monaLisa.getHeight() / 2d,
                new Color(128, 128, 128, 128),
                monaLisa.getWidth() / 10d);

        List<CircleWithColor> circles = new ArrayList<CircleWithColor>();
        for (int i = 0; i < numPoints; ++i) {
            CircleWithColor newCircle = randomizeCircle(middleOfImage, monaLisa.getHeight(), 8);
            while (isOutsideOfBounds(newCircle, monaLisa)) {
                newCircle = randomizeCircle(middleOfImage, monaLisa.getHeight(), 8);
            }

            circles.add(newCircle);
        }

        MonaLisaCirclesHypothesis hypothesis = new MonaLisaCirclesHypothesis(
                monaLisa,
                circles);

        return hypothesis;
    }

    @Override
    public void iterate() {
        //synchronized (hypotheses) {
            //MonaLisaNearestNeighborHypothesis first = hypotheses.first();

        MonaLisaCirclesHypothesis newHypothesis = selfBreed(bestHypothesis);

            if (newHypothesis.valueFunction().longValue() > bestHypothesis.valueFunction().longValue()) {
                System.out.println("New best! " + newHypothesis.valueFunction().longValue() + " old:" + bestHypothesis.valueFunction().longValue());
                bestHypothesis = newHypothesis;
                //hypotheses.add(newHypothesis);
                //hypotheses.remove(hypotheses.last());

                //if (newHypothesis.getParent() != null) {
                //    hypotheses.add(newHypothesis.getParent());
                //}
            }
        //}
    }

    @Override
    public MonaLisaCirclesHypothesis getBestHypothesis() {
        return bestHypothesis;
    }

    private MonaLisaCirclesHypothesis selfBreed(MonaLisaCirclesHypothesis hypothesis) {
        List<CircleWithColor> points = new ArrayList<CircleWithColor>();

        CircleWithColor pointToMutate = hypothesis.getCircles().get(random.nextInt(hypothesis.getCircles().size()));
        points = hypothesis.getCircles();
        points.remove(pointToMutate);
        points.add(randomizeCircle(pointToMutate, mutationPointSpaceVariance, mutationPointColorVariance));

        MonaLisaCirclesHypothesis child = new MonaLisaCirclesHypothesis(
                hypothesis.getMonaLisa(),
                points);

        return child;
    }

    private boolean isOutsideOfBounds(Point newPoint, MonaLisa monaLisa) {
        return newPoint.xInt() < 0 ||
                newPoint.yInt() < 0 ||
                newPoint.xInt() >= monaLisa.getWidth() ||
                newPoint.yInt() >= monaLisa.getHeight();
    }

    private CircleWithColor randomizeCircle(CircleWithColor p, double spaceVariance, int colorVariance) {
        double x;
        double y;
        double radius;
        Color c;

        if (random.nextDouble() < chanceToMutatePoint) {
            x = p.x + (random.nextDouble() - 0.5) * spaceVariance;
            y = p.y + (random.nextDouble() - 0.5) * spaceVariance;
        } else {
            x = p.x;
            y = p.y;
        }

        radius = randomizeRadius(p.radius, radiusVariance);

        //if (random.nextDouble() < chanceToMutatePoint) {
            c = randomizeColor(p.color, colorVariance);
        //} else {
        //    c = p.color;
        //}

        return new CircleWithColor(x, y, c, radius);
    }

    private double randomizeRadius(final double radius, final double radiusVariance) {
        return radius + (random.nextDouble() - 0.5) * radiusVariance;
    }

    private static Color randomizeColor(Color color, int colorVariance) {
        try {
            return new Color(
                    color.getRed() + random.nextInt(colorVariance) - colorVariance / 2,
                    color.getGreen() + random.nextInt(colorVariance) - colorVariance / 2,
                    color.getBlue() + random.nextInt(colorVariance) - colorVariance / 2,
                    color.getAlpha() + random.nextInt(colorVariance) - colorVariance / 2);

        } catch (Exception e) {
            return randomizeColor(color, colorVariance);
        }
    }

}
