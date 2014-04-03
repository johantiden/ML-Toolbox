package se.jtiden.ml.core.circlesadditive;

import se.jtiden.common.images.FastJTImage;
import se.jtiden.common.images.JTColor;
import se.jtiden.common.images.JTImage;
import se.jtiden.common.lang.concurrency.Parallel;
import se.jtiden.ml.core.*;
import se.jtiden.ml.core.algorithm.api.IterativeAlgorithm;
import se.jtiden.common.images.CircleWithColor;
import se.jtiden.ml.core.evaluator.Evaluator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentSkipListSet;


public class AddititveAlgorithm implements IterativeAlgorithm<AdditiveHypothesis, JTImage> {

    private final static Random random = new Random();
    private AdditiveHypothesis bestHypothesis;
    private Evaluator<JTImage> evaluator;
    private int baseAlpha;
    private double currentMaxRadius;
    private int minRadius;

    public AddititveAlgorithm(
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
                new JTColor(random.nextInt(256), random.nextInt(256), random.nextInt(256), random.nextInt(baseAlpha)),
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
            currentMaxRadius *= 0.9999;
            System.out.println("No better found, reducing radius to " +  currentMaxRadius);
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
        Parallel.For(range(20), new Parallel.Operation<Integer>() {
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

}
