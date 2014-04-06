package se.jtiden.ml.imagealgorithms.nearestneighbor;

import se.jtiden.common.images.JTColor;
import se.jtiden.common.images.JTColorImpl;
import se.jtiden.common.images.JTImage;
import se.jtiden.common.images.PointWithColor;
import se.jtiden.common.math.Point;
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
            JTImage targetImage,
            int numPoints,
            double mutationPointSpaceVariance,
            double chanceToMutatePoint, int mutationPointColorVariance, Evaluator<JTImage> evaluator) {
        this.mutationPointColorVariance = mutationPointColorVariance;
        this.mutationPointSpaceVariance = mutationPointSpaceVariance;
        this.chanceToMutatePoint = chanceToMutatePoint;
        this.evaluator = evaluator;
        createRandomInitialHypotheses(targetImage, numPoints);
    }

    private void createRandomInitialHypotheses(JTImage targetImage, int numPoints) {
        bestHypothesis = randomHypothesis(targetImage, numPoints);
    }

    private MonaLisaNearestNeighborHypothesis randomHypothesis(JTImage targetImage, int numPoints) {


        PointWithColor middleOfImage = new PointWithColor(
                targetImage.getWidth() / 2d,
                targetImage.getHeight() / 2d,
                JTColorImpl.GRAY);

        List<PointWithColor> points = new ArrayList<PointWithColor>();
        for (int i = 0; i < numPoints; ++i) {
            PointWithColor newPoint = randomizePoint(middleOfImage, targetImage.getHeight(), 8);
            while (isOutsideOfBounds(newPoint, targetImage.getWidth(), targetImage.getHeight())) {
                newPoint = randomizePoint(middleOfImage, targetImage.getHeight(), 8);
            }

            points.add(newPoint);
        }

        MonaLisaNearestNeighborHypothesis hypothesis = new MonaLisaNearestNeighborHypothesis(
                targetImage,
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
                hypothesis.getTargetImage(),
                points);

        return child;
    }

    private MonaLisaNearestNeighborHypothesis oldSelfBreed(MonaLisaNearestNeighborHypothesis hypothesis) {
        List<PointWithColor> points = new ArrayList<PointWithColor>();
        int width = hypothesis.getTargetImage().getWidth();
        int height = hypothesis.getTargetImage().getHeight();

        for (PointWithColor p : hypothesis.getPoints()) {

            PointWithColor newPoint;
            if (random.nextDouble() > chanceToMutatePoint) {
                newPoint = randomizePoint(p, mutationPointSpaceVariance, mutationPointColorVariance);

                while (isOutsideOfBounds(newPoint, width, height)) {
                    newPoint = randomizePoint(p, mutationPointSpaceVariance, mutationPointColorVariance);
                }
            } else {
                newPoint = p;
            }

            points.add(newPoint);
        }

        MonaLisaNearestNeighborHypothesis child = new MonaLisaNearestNeighborHypothesis(
                hypothesis.getTargetImage(),
                points);


        return child;
    }

    private boolean isOutsideOfBounds(Point newPoint, int width, int height) {

        return newPoint.xInt() < 0 ||
                newPoint.yInt() < 0 ||
                newPoint.xInt() >= width ||
                newPoint.yInt() >= height;
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
        c = randomizeColor(p.getColor(), colorVariance);
        //} else {
        //    c = p.color;
        //}

        return new PointWithColor(x, y, c);
    }

    private static JTColor randomizeColor(JTColor color, int colorVariance) {
        try {
            return new JTColorImpl(
                    color.getR() + random.nextInt(colorVariance) - colorVariance / 2,
                    color.getG() + random.nextInt(colorVariance) - colorVariance / 2,
                    color.getB() + random.nextInt(colorVariance) - colorVariance / 2);

        } catch (Exception e) {
            return randomizeColor(color, colorVariance);
        }
    }

}
