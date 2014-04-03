package se.jtiden.ml.core.impl.circlesadditive;

import se.jtiden.ml.core.api.*;
import se.jtiden.ml.core.impl.FastJTImage;
import se.jtiden.ml.core.impl.MonaLisa;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class AddititveAlgorithm implements IterativeAlgorithm<AdditiveHypothesis, JTImage> {

    private final static Random random = new Random();
    private AdditiveHypothesis bestHypothesis;
    private Evaluator<JTImage> evaluator;
    private int baseAlpha;

    public AddititveAlgorithm(
            MonaLisa monaLisa,
            Evaluator<JTImage> evaluator, int baseAlpha) {
        this.evaluator = evaluator;
        this.baseAlpha = baseAlpha;
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
                new JTColor(random.nextInt(256), random.nextInt(256), random.nextInt(256), random.nextInt(230)),
                random.nextInt(monaLisa.getWidth()/8));
    }

    @Override
    public void iterate() {
        List<AdditiveHypothesis> newHypotheses = selfBreed(bestHypothesis);

        for (AdditiveHypothesis child : newHypotheses) {

            if (child.valueFunction() > bestHypothesis.valueFunction()) {
                System.out.println("New best! " + child.valueFunction() +
                        " old:" + bestHypothesis.valueFunction());
                bestHypothesis = child;
            }
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

    private List<AdditiveHypothesis> selfBreed(AdditiveHypothesis hypothesis) {

        List<AdditiveHypothesis> list = new ArrayList<AdditiveHypothesis>();
        for (int i = 0; i < 20; ++i) {
            list.add(mutateAddPoint(hypothesis));
        }

        return list;
    }


    private AdditiveHypothesis mutateAddPoint(final AdditiveHypothesis hypothesis) {
        AdditiveHypothesis child = hypothesis.copy();

        child.draw(newRandomCircle(hypothesis.getMonaLisa()));

        return child;
    }

}
