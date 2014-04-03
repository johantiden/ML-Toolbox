package se.jtiden.ml.imagealgorithms.nearestneighbor;

import se.jtiden.common.images.JTColor;
import se.jtiden.common.images.JTImage;
import se.jtiden.common.images.PointWithColor;
import se.jtiden.common.math.Point;
import se.jtiden.ml.imagealgorithms.MonaLisa;
import se.jtiden.ml.imagealgorithms.algorithm.api.IterativeAlgorithm;
import se.jtiden.ml.imagealgorithms.evaluator.Evaluator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MonaLisaNearestNeighborAlgorithm implements IterativeAlgorithm<MonaLisaNearestNeighborHypothesis, JTImage> {

    private final double mutationPointSpaceVariance;
    private final static Random random = new Random();
    private final double chanceToMutatePoint;
    private final int mutationPointColorVariance;
    private MonaLisaNearestNeighborHypothesis bestHypothesis;
    private Evaluator<JTImage> evaluator;

    public MonaLisaNearestNeighborAlgorithm(
            MonaLisa monaLisa,
            int numPoints,
            double mutationPointVariance,
            double chanceToMutatePoint, int mutationPointColorVariance, Evaluator<JTImage> evaluator) {
        this.mutationPointColorVariance = mutationPointColorVariance;
        this.mutationPointSpaceVariance = mutationPointVariance;
        this.chanceToMutatePoint = chanceToMutatePoint;
        this.evaluator = evaluator;
        createRandomInitialHypotheses(monaLisa, numPoints);
    }

    private void createRandomInitialHypotheses(MonaLisa monaLisa, int numPoints) {
         bestHypothesis = randomHypothesis(monaLisa, numPoints);
    }

    private MonaLisaNearestNeighborHypothesis randomHypothesis(MonaLisa monaLisa, int numPoints) {



        PointWithColor middleOfImage = new PointWithColor(
                monaLisa.getWidth() / 2d,
                monaLisa.getHeight() / 2d,
                JTColor.GRAY);

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
                points);

        return hypothesis;
    }

    @Override
    public void iterate() {
        //synchronized (hypotheses) {
            //MonaLisaNearestNeighborHypothesis first = hypotheses.first();

            MonaLisaNearestNeighborHypothesis newHypothesis = selfBreed(bestHypothesis);

            if (newHypothesis.valueFunction() > bestHypothesis.valueFunction()) {
                System.out.println("New best! " + newHypothesis.valueFunction() + " old:" + bestHypothesis.valueFunction());
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

    @Override
    public Evaluator<JTImage> getEvaluator() {
        return evaluator;
    }

    private MonaLisaNearestNeighborHypothesis selfBreed(MonaLisaNearestNeighborHypothesis hypothesis) {
        List<PointWithColor> points;

        PointWithColor pointToMutate = hypothesis.getPoints().get(random.nextInt(hypothesis.getPoints().size()));
        points = hypothesis.getPoints();
        points.remove(pointToMutate);
        points.add(randomizePoint(pointToMutate, mutationPointSpaceVariance, mutationPointColorVariance));

        MonaLisaNearestNeighborHypothesis child = new MonaLisaNearestNeighborHypothesis(
                hypothesis.getMonaLisa(),
                points);

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
                points);


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
        JTColor c;

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

    private static JTColor randomizeColor(JTColor color, int colorVariance) {
        try {
            return new JTColor(
                    color.r + random.nextInt(colorVariance) - colorVariance / 2,
                    color.g + random.nextInt(colorVariance) - colorVariance / 2,
                    color.b + random.nextInt(colorVariance) - colorVariance / 2);

        } catch (Exception e) {
            return randomizeColor(color, colorVariance);
        }
    }

}
