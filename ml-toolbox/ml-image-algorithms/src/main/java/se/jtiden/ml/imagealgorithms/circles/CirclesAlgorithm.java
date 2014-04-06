package se.jtiden.ml.imagealgorithms.circles;

import se.jtiden.common.images.CircleWithColor;
import se.jtiden.common.images.JTColor;
import se.jtiden.common.images.JTColorImpl;
import se.jtiden.common.images.JTImage;
import se.jtiden.common.math.Point;
import se.jtiden.ml.imagealgorithms.algorithm.api.IterativeAlgorithm;
import se.jtiden.ml.imagealgorithms.evaluator.Evaluator;

import java.util.*;

public class CirclesAlgorithm implements IterativeAlgorithm<CirclesHypothesis, JTImage> {

    public static final int ALPHA = 255;
    private final double mutationPointSpaceVariance;
    private final static Random random = new Random();
    private final double chanceToMutatePoint;
    private final int maxNumPoints;
    private final double chanceToCreatePoint;
    private final double chanceToDeletePoint;
    private final int mutationPointColorVariance;
    private CirclesHypothesis bestHypothesis;
    private final double radiusVariance;
    private Evaluator<JTImage> evaluator;

    public CirclesAlgorithm(
            JTImage targetImage,
            int minNumPoints,
            int maxNumPoints,
            double mutationPointSpaceVariance,
            double chanceToMutatePoint,
            double chanceToCreatePoint,
            double chanceToDeletePoint,
            int mutationPointColorVariance,
            double radiusVariance, Evaluator<JTImage> evaluator) {
        this.maxNumPoints = maxNumPoints;
        this.chanceToCreatePoint = chanceToCreatePoint;
        this.chanceToDeletePoint = chanceToDeletePoint;
        this.mutationPointColorVariance = mutationPointColorVariance;
        this.mutationPointSpaceVariance = mutationPointSpaceVariance;
        this.chanceToMutatePoint = chanceToMutatePoint;
        this.radiusVariance = radiusVariance;
        this.evaluator = evaluator;
        createRandomInitialHypotheses(targetImage, minNumPoints);
    }

    private void createRandomInitialHypotheses(JTImage targetImage, int numPoints) {
        bestHypothesis = randomHypothesis(targetImage, numPoints);
    }

    private CirclesHypothesis randomHypothesis(JTImage targetImage, int numPoints) {
        List<CircleWithColor> circles = new ArrayList<CircleWithColor>();
        for (int i = 0; i < numPoints; ++i) {
            CircleWithColor newCircle = newRandomCircle(targetImage);
            while (isOutsideOfBounds(newCircle, targetImage)) {
                newCircle = newRandomCircle(targetImage);
            }

            circles.add(newCircle);
        }

        CirclesHypothesis hypothesis = new CirclesHypothesis(
                circles,
                getEvaluator());

        return hypothesis;
    }

    private CircleWithColor newRandomCircle(JTImage targetImage) {
        CircleWithColor newCircle = new CircleWithColor(
                random.nextInt(targetImage.getWidth()),
                random.nextInt(targetImage.getHeight()),
                new JTColorImpl(random.nextInt(256), random.nextInt(256), random.nextInt(256)),
                random.nextInt(targetImage.getWidth()));
        return randomizeCircle(newCircle);
    }

    @Override
    public void iterate() {
        List<CirclesHypothesis> newHypotheses = selfBreed(bestHypothesis);

        for (CirclesHypothesis child : newHypotheses) {

            if (child.valueFunction() >= bestHypothesis.valueFunction()) {
                System.out.println("New best! " + child.valueFunction() +
                        " old:" + bestHypothesis.valueFunction() +
                        " points: " + bestHypothesis.countCircles());
                bestHypothesis = child;
            }
        }
    }

    @Override
    public CirclesHypothesis getBestHypothesis() {
        return bestHypothesis;
    }

    @Override
    public Evaluator<JTImage> getEvaluator() {
        return evaluator;
    }

    private List<CirclesHypothesis> selfBreed(CirclesHypothesis hypothesis) {
        CirclesHypothesis added = mutateAddPoint(hypothesis);
        CirclesHypothesis mutated = mutateMutatePoint(hypothesis);
        CirclesHypothesis removedPoint = mutateRemovePoint(hypothesis);
        CirclesHypothesis removedPointUseless = removeUselessPoints(hypothesis);

        return Arrays.asList(added, mutated, removedPoint, removedPointUseless);
    }


    private CirclesHypothesis mutateMutatePoint(final CirclesHypothesis hypothesis) {
        CirclesHypothesis child = hypothesis.copy();

        CircleWithColor pointToMutate = child.getCircles().get(random.nextInt(child.getCircles().size()));
        child.getCircles().remove(pointToMutate);

        int index = child.getCircles().size() > 0 ? random.nextInt(child.getCircles().size()) : 0;
        child.getCircles().add(index, randomizeCircle(pointToMutate));
        return child;
    }

    private CirclesHypothesis mutateRemovePoint(final CirclesHypothesis hypothesis) {
        CirclesHypothesis child = hypothesis.copy();
        int index2 = random.nextInt(child.getCircles().size());
        child.getCircles().remove(index2);
        return child;
    }

    private CirclesHypothesis mutateAddPoint(final CirclesHypothesis hypothesis) {
        CirclesHypothesis child = hypothesis.copy();

        if (child.getCircles().size() < maxNumPoints) {
            int index = random.nextInt(child.getCircles().size());
            child.getCircles().add(index, newRandomCircle(hypothesis.getTargetImage()));
        }
        return child;
    }

    private CirclesHypothesis removeUselessPoints(final CirclesHypothesis hypothesis) {
        CirclesHypothesis child = hypothesis.copy();
        Iterator<CircleWithColor> iterator = child.getCircles().iterator();
        while (iterator.hasNext()) {
            CircleWithColor circle = iterator.next();
            if (circle.getRadius() < 1) {
                iterator.remove();
            }
        }
        return child;
    }

    private boolean isOutsideOfBounds(Point newPoint, JTImage targetImage) {
        return newPoint.xInt() < 0 ||
                newPoint.yInt() < 0 ||
                newPoint.xInt() >= targetImage.getWidth() ||
                newPoint.yInt() >= targetImage.getHeight();
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

        radius = randomizeRadius(p.getRadius(), radiusVariance);


        if (random.nextDouble() < chanceToMutatePoint) {
            c = randomizeColor(p.getColor(), mutationPointColorVariance);
        } else {
            c = p.getColor();
        }

        return new CircleWithColor(x, y, c, radius);
    }

    private double randomizeRadius(double radius, double radiusVariance) {
        if (random.nextDouble() < chanceToMutatePoint) {
            return Math.min(radius + (random.nextDouble() - 0.5) * radiusVariance, 1000000);
        } else {
            return radius;
        }
    }

    private static JTColor randomizeColor(JTColor color, int colorVariance) {

        return new JTColorImpl(
                Math.max(0, Math.min(color.getR() + random.nextInt(colorVariance) - colorVariance / 2, ALPHA)),
                Math.max(0, Math.min(color.getG() + random.nextInt(colorVariance) - colorVariance / 2, ALPHA)),
                Math.max(0, Math.min(color.getB() + random.nextInt(colorVariance) - colorVariance / 2, ALPHA)));


    }

}
