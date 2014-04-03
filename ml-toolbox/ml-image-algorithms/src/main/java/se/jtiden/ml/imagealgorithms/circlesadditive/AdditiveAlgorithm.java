package se.jtiden.ml.imagealgorithms.circlesadditive;

import se.jtiden.common.concurrency.Operation;
import se.jtiden.common.concurrency.Parallel;
import se.jtiden.common.images.*;
import se.jtiden.ml.imagealgorithms.MonaLisa;
import se.jtiden.ml.imagealgorithms.algorithm.api.IterativeAlgorithm;
import se.jtiden.ml.imagealgorithms.evaluator.Evaluator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentSkipListSet;


public class AdditiveAlgorithm implements IterativeAlgorithm<AdditiveHypothesis, JTImage> {

    private static final Random random = new Random();
    public static final double RADIUS_SHRINK_RATE_PER_MISS = 0.9999;
    private AdditiveHypothesis bestHypothesis;
    private final Evaluator<JTImage> evaluator;
    private final int baseAlpha;
    private double currentMaxRadius;
    private final double minRadius;

    public AdditiveAlgorithm(
            MonaLisa monaLisa,
            Evaluator<JTImage> evaluator, int baseAlpha, final int minRadius) {
        this.evaluator = evaluator;
        this.baseAlpha = baseAlpha;
        this.minRadius = minRadius;
        this.currentMaxRadius = monaLisa.getWidth() / 2;
        createRandomInitialHypotheses(monaLisa);
    }

    private void createRandomInitialHypotheses(MonaLisa monaLisa) {
        bestHypothesis = new AdditiveHypothesis(
                monaLisa,
                new FastJTImage(monaLisa.getWidth(), monaLisa.getHeight()),
                getEvaluator());
    }

    private CircleWithColor newRandomCircle(MonaLisa monaLisa) {
        return new CircleWithColor(
                random.nextInt((int) (monaLisa.getWidth())),
                random.nextInt((int) (monaLisa.getHeight())),
                new JTColorImpl(random.nextInt(256), random.nextInt(256), random.nextInt(256), random.nextInt(baseAlpha)),
                random.nextInt((int) currentMaxRadius));
    }

    @Override
    public void iterate() {
        //System.out.println("iterate");
        Collection<AdditiveHypothesis> newHypotheses = selfBreed(bestHypothesis);


        boolean foundBetter = false;
        for (AdditiveHypothesis child : newHypotheses) {

            if (child.valueFunction() > bestHypothesis.valueFunction()) {
                foundBetter = true;
                System.out.println("New best! " + child.valueFunction() +
                        " old:" + bestHypothesis.valueFunction());
                bestHypothesis = child;
            }
        }

        if (!foundBetter && currentMaxRadius > minRadius) {
            currentMaxRadius *= RADIUS_SHRINK_RATE_PER_MISS;
            System.out.println("No better found, reducing radius to " + currentMaxRadius);
        }
    }

    @Override
    public AdditiveHypothesis getBestHypothesis() {
        return bestHypothesis;
    }

    @Override
    public Evaluator<JTImage> getEvaluator() {
        return evaluator;
    }

    private Collection<AdditiveHypothesis> selfBreed(final AdditiveHypothesis hypothesis) {
        final Collection<AdditiveHypothesis> list = new ConcurrentSkipListSet<AdditiveHypothesis>();
        Parallel.For(range(20), new Operation<Integer>() {
            @Override
            public void perform(final Integer pParameter) {
                AdditiveHypothesis child = mutateAddPoint(hypothesis);
                list.add(child);
            }
        });

        return list;
    }

    private List<Integer> range(final int maxExclusive) {
        List<Integer> range = new ArrayList<Integer>();
        for (int i = 0; i < maxExclusive; ++i) {
            range.add(i);
        }
        return range;
    }


    private AdditiveHypothesis mutateAddPoint(final AdditiveHypothesis hypothesis) {
        AdditiveHypothesis child = hypothesis.copy();

        child.draw(newRandomCircle(hypothesis.getMonaLisa()));

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
}
