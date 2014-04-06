package se.jtiden.ml.imagealgorithms.circlesadditive;

import se.jtiden.common.concurrency.Operation;
import se.jtiden.common.concurrency.ParallelPooled;
import se.jtiden.common.images.*;
import se.jtiden.common.math.Lists;
import se.jtiden.ml.imagealgorithms.algorithm.api.Hypothesis;
import se.jtiden.ml.imagealgorithms.algorithm.api.IterativeAlgorithm;
import se.jtiden.ml.imagealgorithms.evaluator.Evaluator;

import java.util.Collection;
import java.util.Random;
import java.util.concurrent.ConcurrentSkipListSet;


public class AdditiveAlgorithm implements IterativeAlgorithm<AdditiveHypothesis, JTImage> {

    private static final Random random = new Random();
    public static final double RADIUS_SHRINK_RATE_PER_MISS = 0.999;
    public static final int NUM_CHILDREN_PER_GENERATION = 20;
    private final Evaluator<JTImage> evaluator;
    private final int width;
    private final int height;

    private double currentMaxRadius;
    private Hypothesis bestHypothesis;



    public AdditiveAlgorithm(
            Evaluator<JTImage> evaluator) {
        this.evaluator = evaluator;
        JTImage target = evaluator.getTarget();
        width = target.getWidth();
        height = target.getHeight();
        currentMaxRadius = target.getWidth() / 2;
        createRandomInitialHypotheses(target);
    }

    private void createRandomInitialHypotheses(JTImage targetImage) {
        bestHypothesis = new AdditiveHypothesis(
                new FastJTImage(targetImage.getWidth(), targetImage.getHeight()),
                evaluator);
    }



    @Override
    public void iterate() {
        //System.out.println("iterate");
        Collection<Hypothesis> newHypotheses = selfBreed(bestHypothesis);
        boolean shrinkRadius = true;
        for (Hypothesis child : newHypotheses) {

            if (child.valueFunction() > bestHypothesis.valueFunction()) {
                shrinkRadius = false;
                System.out.println("New best! " + child.valueFunction() +
                        " old:" + bestHypothesis.valueFunction());
                bestHypothesis = child;
            }
        }

        if (shrinkRadius && currentMaxRadius > AdditiveContextFactory.MIN_RADIUS) {
            currentMaxRadius *= RADIUS_SHRINK_RATE_PER_MISS;
            System.out.println("No better found, reducing radius to " + currentMaxRadius);
        }
    }

    @Override
    public AdditiveHypothesis getBestHypothesis() {
        return (AdditiveHypothesis)bestHypothesis;
    }

    @Override
    public Evaluator<JTImage> getEvaluator() {
        return evaluator;
    }

    private Collection<Hypothesis> selfBreed(Hypothesis hypothesis) {
        Collection<Hypothesis> list = new ConcurrentSkipListSet<Hypothesis>();
        new ParallelPooled().For(Lists.range(NUM_CHILDREN_PER_GENERATION), new MutateAndAddToListOperation(hypothesis, list));

        return list;
    }


    @Override
    public String toString() {
        return "AdditiveAlgorithm{" +
                "evaluator=" + evaluator +
                ", width=" + width +
                ", height=" + height +
                ", currentMaxRadius=" + currentMaxRadius +
                ", bestHypothesis=" + bestHypothesis +
                '}';
    }

    private class MutateAndAddToListOperation implements Operation<Integer> {
        private final Hypothesis hypothesis;
        private final Collection<Hypothesis> list;

        MutateAndAddToListOperation(Hypothesis hypothesis, Collection<Hypothesis> list) {
            this.hypothesis = hypothesis;
            this.list = list;
        }

        @Override
        public void perform(Integer pParameter) {
            Hypothesis child = mutateAddPoint(hypothesis);
            list.add(child);
        }

        private Hypothesis mutateAddPoint(Hypothesis hypothesis) {
            Hypothesis child = hypothesis.copy();


            ((AdditiveHypothesis)child).draw(newRandomCircle(width, height));

            return child;
        }

        private CircleWithColor newRandomCircle(int width, int height) {
            return new CircleWithColorImpl(
                    random.nextInt(width),
                    random.nextInt(height),
                    new JTColorImpl(random.nextInt(256), random.nextInt(256), random.nextInt(256), random.nextInt(AdditiveContextFactory.BASE_ALPHA)),
                    random.nextInt((int) currentMaxRadius));
        }
    }
}
