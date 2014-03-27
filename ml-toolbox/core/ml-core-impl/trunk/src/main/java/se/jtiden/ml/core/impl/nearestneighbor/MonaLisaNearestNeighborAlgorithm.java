package se.jtiden.ml.core.impl.nearestneighbor;

import se.jtiden.ml.core.api.IterativeAlgorithm;
import se.jtiden.ml.core.api.Point;
import se.jtiden.ml.core.api.PointWithColor;
import se.jtiden.ml.core.impl.MonaLisa;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MonaLisaNearestNeighborAlgorithm implements IterativeAlgorithm<MonaLisaNearestNeighborHypothesis> {

    private final double mutationPointSpaceVariance;
    private final int numNeighborsForClassification;
    private final static Random random = new Random();
    private final double chanceToMutatePoint;
    private final int mutationPointColorVariance;
    private MonaLisaNearestNeighborHypothesis bestHypothesis;

    public MonaLisaNearestNeighborAlgorithm(
            MonaLisa monaLisa,
            int numPoints,
            double mutationPointVariance,
            int numNeighborsForClassification,
            double chanceToMutatePoint, int mutationPointColorVariance) {
        this.mutationPointColorVariance = mutationPointColorVariance;
        this.mutationPointSpaceVariance = mutationPointVariance;
        this.numNeighborsForClassification = numNeighborsForClassification;
        this.chanceToMutatePoint = chanceToMutatePoint;
        createRandomInitialHypotheses(monaLisa, numPoints);
    }

    private void createRandomInitialHypotheses(MonaLisa monaLisa, int numPoints) {
         bestHypothesis = randomHypothesis(monaLisa, numPoints);
    }

    private MonaLisaNearestNeighborHypothesis randomHypothesis(MonaLisa monaLisa, int numPoints) {



        PointWithColor middleOfImage = new PointWithColor(
                monaLisa.getWidth() / 2d,
                monaLisa.getHeight() / 2d,
                new Color(128, 128, 128));

        List<PointWithColor> points = new ArrayList<PointWithColor>();
        for (int i = 0; i < numPoints; ++i) {
            PointWithColor newPoint = randomizePoint(middleOfImage, monaLisa.getHeight(), 8);
            while (isOutsideOfBounds(newPoint, monaLisa)) {
                newPoint = randomizePoint(middleOfImage, monaLisa.getHeight(), 8);
            }

            points.add(newPoint);
        }

        MonaLisaNearestNeighborHypothesis hypothesis = new MonaLisaNearestNeighborHypothesis(
                monaLisa,
                points,
                numNeighborsForClassification);

        return hypothesis;
    }

    @Override
    public void iterate() {
        //synchronized (hypotheses) {
            //MonaLisaNearestNeighborHypothesis first = hypotheses.first();

            MonaLisaNearestNeighborHypothesis newHypothesis = selfBreed(bestHypothesis);

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
    public MonaLisaNearestNeighborHypothesis getBestHypothesis() {
        return bestHypothesis;
    }

    private MonaLisaNearestNeighborHypothesis selfBreed(MonaLisaNearestNeighborHypothesis hypothesis) {
        List<PointWithColor> points = new ArrayList<PointWithColor>();

        PointWithColor pointToMutate = hypothesis.getPoints().get(random.nextInt(hypothesis.getPoints().size()));
        points = hypothesis.getPoints();
        points.remove(pointToMutate);
        points.add(randomizePoint(pointToMutate, mutationPointSpaceVariance, mutationPointColorVariance));

        MonaLisaNearestNeighborHypothesis child = new MonaLisaNearestNeighborHypothesis(
                hypothesis.getMonaLisa(),
                points,
                numNeighborsForClassification);

        return child;
    }

    private MonaLisaNearestNeighborHypothesis oldSelfBreed(MonaLisaNearestNeighborHypothesis hypothesis) {
        List<PointWithColor> points = new ArrayList<PointWithColor>();
        for (PointWithColor p : hypothesis.getPoints()) {

            PointWithColor newPoint;
            if (random.nextDouble() > chanceToMutatePoint) {

                newPoint = randomizePoint(p, mutationPointSpaceVariance, mutationPointColorVariance);
                while (isOutsideOfBounds(newPoint, hypothesis.getMonaLisa())) {
                    newPoint = randomizePoint(p, mutationPointSpaceVariance, mutationPointColorVariance);
                }
            } else {
               newPoint = p;
            }

            points.add(newPoint);
        }

        MonaLisaNearestNeighborHypothesis child = new MonaLisaNearestNeighborHypothesis(
                hypothesis.getMonaLisa(),
                points,
                numNeighborsForClassification);


        return child;
    }

    private boolean isOutsideOfBounds(Point newPoint, MonaLisa monaLisa) {
        return newPoint.xInt() < 0 ||
                newPoint.yInt() < 0 ||
                newPoint.xInt() >= monaLisa.getWidth() ||
                newPoint.yInt() >= monaLisa.getHeight();
    }

    private PointWithColor randomizePoint(PointWithColor p, double spaceVariance, int colorVariance) {
        double x;
        double y;
        Color c;

        if (random.nextDouble() < chanceToMutatePoint) {
            x = p.x + (random.nextDouble() - 0.5) * spaceVariance;
            y = p.y + (random.nextDouble() - 0.5) * spaceVariance;
        } else {
            x = p.x;
            y = p.y;
        }

        //if (random.nextDouble() < chanceToMutatePoint) {
            c = randomizeColor(p.color, colorVariance);
        //} else {
        //    c = p.color;
        //}

        return new PointWithColor(x, y, c);
    }

    private static Color randomizeColor(Color color, int colorVariance) {
        try {
            return new Color(
                    color.getRed() + random.nextInt(colorVariance) - colorVariance / 2,
                    color.getGreen() + random.nextInt(colorVariance) - colorVariance / 2,
                    color.getBlue() + random.nextInt(colorVariance) - colorVariance / 2);

        } catch (Exception e) {
            return randomizeColor(color, colorVariance);
        }
    }

}
