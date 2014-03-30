package se.jtiden.ml.core.impl.circles;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import se.jtiden.ml.core.api.*;
import se.jtiden.ml.core.api.Point;
import se.jtiden.ml.core.impl.MonaLisa;

public class MonaLisaCirclesAlgorithm implements IterativeAlgorithm<MonaLisaCirclesHypothesis, JTImage> {

    private final double mutationPointSpaceVariance;
    private final static Random random = new Random();
    private final double chanceToMutatePoint;
    private final int maxNumPoints;
    private final double chanceToCreatePoint;
    private final int mutationPointColorVariance;
    private MonaLisaCirclesHypothesis bestHypothesis;
    private final double radiusVariance;
    private Evaluator<JTImage> evaluator;

    public MonaLisaCirclesAlgorithm(
            MonaLisa monaLisa,
            int minNumPoints,
            int maxNumPoints,
            double mutationPointVariance,
            double chanceToMutatePoint,
            double chanceToCreatePoint,
            int mutationPointColorVariance,
            final double radiusVariance, Evaluator<JTImage> evaluator) {
        this.maxNumPoints = maxNumPoints;
        this.chanceToCreatePoint = chanceToCreatePoint;
        this.mutationPointColorVariance = mutationPointColorVariance;
        this.mutationPointSpaceVariance = mutationPointVariance;
        this.chanceToMutatePoint = chanceToMutatePoint;
        this.radiusVariance = radiusVariance;
        this.evaluator = evaluator;
        createRandomInitialHypotheses(monaLisa, minNumPoints);
    }

    private void createRandomInitialHypotheses(MonaLisa monaLisa, int numPoints) {
         bestHypothesis = randomHypothesis(monaLisa, numPoints);
    }

    private MonaLisaCirclesHypothesis randomHypothesis(MonaLisa monaLisa, int numPoints) {
        List<CircleWithColor> circles = new ArrayList<CircleWithColor>();
        for (int i = 0; i < numPoints; ++i) {
            CircleWithColor newCircle = newRandomCircle(monaLisa);
            while (isOutsideOfBounds(newCircle, monaLisa)) {
                newCircle = newRandomCircle(monaLisa);
            }

            circles.add(newCircle);
        }

        MonaLisaCirclesHypothesis hypothesis = new MonaLisaCirclesHypothesis(
                monaLisa,
                circles,
                this);

        return hypothesis;
    }

    private CircleWithColor newRandomCircle(MonaLisa monaLisa) {
        CircleWithColor newCircle = new CircleWithColor(
                random.nextInt(monaLisa.getWidth()),
                random.nextInt(monaLisa.getHeight()),
                new JTColor(random.nextInt(256), random.nextInt(256), random.nextInt(256)),
                random.nextInt(monaLisa.getWidth()));
        return randomizeCircle(newCircle);
    }

    @Override
    public void iterate() {
        //synchronized (hypotheses) {
            //MonaLisaNearestNeighborHypothesis first = hypotheses.first();

        MonaLisaCirclesHypothesis newHypothesis = selfBreed(bestHypothesis);

            if (newHypothesis.valueFunction() > bestHypothesis.valueFunction()) {
                System.out.println("New best! " + newHypothesis.valueFunction() +
                        " old:" + bestHypothesis.valueFunction() +
                        " points: " + bestHypothesis.countCircles());
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

    @Override
    public Evaluator<JTImage> getEvaluator() {
        return evaluator;
    }

    private MonaLisaCirclesHypothesis selfBreed(MonaLisaCirclesHypothesis hypothesis) {
        List<CircleWithColor> points = hypothesis.getCircles();
        CircleWithColor pointToMutate = points.get(random.nextInt(points.size()));


        while (points.size() < maxNumPoints &&
                random.nextDouble() < chanceToCreatePoint) {
            int index2 = random.nextInt(points.size());
            points.add(index2, newRandomCircle(hypothesis.getMonaLisa()));
        }


        points.remove(pointToMutate);

        int index = points.size() > 0 ? random.nextInt(points.size()) : 0;
        points.add(index, randomizeCircle(pointToMutate));



        MonaLisaCirclesHypothesis child = new MonaLisaCirclesHypothesis(
                hypothesis.getMonaLisa(),
                points,
                this);

        removeUselessPoints(hypothesis);

        return child;
    }

    private void removeUselessPoints(MonaLisaCirclesHypothesis hypothesis) {
        Iterator<CircleWithColor> iterator = hypothesis.getCircles().iterator();
        while (iterator.hasNext()) {
            CircleWithColor circle = iterator.next();
            if (circle.radius < 1) {
                iterator.remove();
            }
        }
    }

    private boolean isOutsideOfBounds(Point newPoint, MonaLisa monaLisa) {
        return newPoint.xInt() < 0 ||
                newPoint.yInt() < 0 ||
                newPoint.xInt() >= monaLisa.getWidth() ||
                newPoint.yInt() >= monaLisa.getHeight();
    }

    private CircleWithColor randomizeCircle(CircleWithColor p) {
        double x;
        double y;
        double radius;
        JTColor c;

        if (random.nextDouble() < chanceToMutatePoint) {
            x = p.x + (random.nextDouble() - 0.5) * mutationPointSpaceVariance;
            y = p.y + (random.nextDouble() - 0.5) * mutationPointSpaceVariance;
        } else {
            x = p.x;
            y = p.y;
        }

        radius = randomizeRadius(p.radius, radiusVariance);



        if (random.nextDouble() < chanceToMutatePoint) {
            c = randomizeColor(p.color, mutationPointColorVariance);
        } else {
            c = p.color;
        }

        return new CircleWithColor(x, y, c, radius);
    }

    private double randomizeRadius(final double radius, final double radiusVariance) {
        if (random.nextDouble() < chanceToMutatePoint) {
            return Math.min(radius + (random.nextDouble() - 0.5) * radiusVariance, 1000000);
        } else {
            return radius;
        }
    }

    private static JTColor randomizeColor(JTColor color, int colorVariance) {

        return new JTColor(
                Math.max(0, Math.min(color.r + random.nextInt(colorVariance) - colorVariance / 2, 255)),
                Math.max(0, Math.min(color.g + random.nextInt(colorVariance) - colorVariance / 2, 255)),
                Math.max(0, Math.min(color.b + random.nextInt(colorVariance) - colorVariance / 2, 255)));


    }

}
