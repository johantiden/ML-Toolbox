package se.jtiden.ml.core.impl.circlesadditive;

import se.jtiden.ml.core.api.*;
import se.jtiden.ml.core.impl.FastJTImage;
import se.jtiden.ml.core.impl.MonaLisa;

import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class AddititveAlgorithm implements IterativeAlgorithm<AdditiveHypothesis, JTImage> {

    private final static Random random = new Random();
    private AdditiveHypothesis bestHypothesis;
    private Evaluator<JTImage> evaluator;

    public AddititveAlgorithm(
            MonaLisa monaLisa,
            Evaluator<JTImage> evaluator) {
        this.evaluator = evaluator;
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
                random.nextInt(monaLisa.getWidth() * 2 - monaLisa.getWidth()/2),
                random.nextInt(monaLisa.getHeight() * 2 - monaLisa.getHeight()/2),
                new JTColor(random.nextInt(256), random.nextInt(256), random.nextInt(256), 10),
                random.nextInt(monaLisa.getWidth()*3));
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
        AdditiveHypothesis added = mutateAddPoint(hypothesis);

        return Arrays.asList(added);
    }


    private AdditiveHypothesis mutateAddPoint(final AdditiveHypothesis hypothesis) {
        AdditiveHypothesis child = hypothesis.copy();

        child.draw(newRandomCircle(hypothesis.getMonaLisa()));

        return child;
    }

}
