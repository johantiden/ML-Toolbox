package se.jtiden.ml.imagealgorithms.circlesadditive;

import se.jtiden.common.concurrency.Operation;
import se.jtiden.common.concurrency.Parallel;
import se.jtiden.common.images.CircleWithColor;
import se.jtiden.common.images.FastJTImage;
import se.jtiden.common.images.JTColorImpl;
import se.jtiden.common.images.JTImage;
import se.jtiden.ml.imagealgorithms.algorithm.api.Hypothesis;
import se.jtiden.ml.imagealgorithms.algorithm.api.IterativeAlgorithm;
import se.jtiden.ml.imagealgorithms.evaluator.Evaluator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentSkipListSet;


public class AdditiveAlgorithm implements IterativeAlgorithm<AdditiveHypothesis, JTImage> {

    private static final Random random = new Random();
    public static final double RADIUS_SHRINK_RATE_PER_MISS = 0.999;
    private final Evaluator<JTImage> evaluator;
    private final int baseAlpha;
    private final int width;
    private final int height;
    private final double minRadius;

    private double currentMaxRadius;
    private Hypothesis bestHypothesis;

    public AdditiveAlgorithm(
            JTImage targetImage,
            Evaluator<JTImage> evaluator, int baseAlpha, final int minRadius) {
        this.evaluator = evaluator;
        this.baseAlpha = baseAlpha;
        this.minRadius = minRadius;
        width = evaluator.getTarget().getWidth();
        height = evaluator.getTarget().getHeight();
        this.currentMaxRadius = targetImage.getWidth() / 2;
        createRandomInitialHypotheses(targetImage);
    }

    private void createRandomInitialHypotheses(JTImage targetImage) {
        bestHypothesis = new AdditiveHypothesis(
                new FastJTImage(targetImage.getWidth(), targetImage.getHeight()),
                getEvaluator());
    }

    private CircleWithColor newRandomCircle(int width, int height) {
        return new CircleWithColor(
                random.nextInt(width),
                random.nextInt(height),
                new JTColorImpl(random.nextInt(256), random.nextInt(256), random.nextInt(256), random.nextInt(baseAlpha)),
                random.nextInt((int) currentMaxRadius));
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

        if (shrinkRadius && currentMaxRadius > minRadius) {
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

    private Collection<Hypothesis> selfBreed(final Hypothesis hypothesis) {
        final Collection<Hypothesis> list = new ConcurrentSkipListSet<Hypothesis>();
        Parallel.For(range(20), new MutateAndAddToListOperation(hypothesis, list));

        return list;
    }

    private List<Integer> range(final int maxExclusive) {
        List<Integer> range = new ArrayList<Integer>();
        for (int i = 0; i < maxExclusive; ++i) {
            range.add(i);
        }
        return range;
    }


    private Hypothesis mutateAddPoint(Hypothesis hypothesis) {
        Hypothesis child = hypothesis.copy();


        ((AdditiveHypothesis)child).draw(newRandomCircle(width, height));

        return child;
    }

    @Override
    public String toString() {
        return "AdditiveAlgorithm{" +
                "bestHypothesis=" + bestHypothesis +
                ", evaluator=" + evaluator +
                ", baseAlpha=" + baseAlpha +
                ", currentMaxRadius=" + currentMaxRadius +
                ", minRadius=" + minRadius +
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
    }
}
