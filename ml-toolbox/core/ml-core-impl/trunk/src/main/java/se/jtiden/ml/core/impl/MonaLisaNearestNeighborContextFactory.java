package se.jtiden.ml.core.impl;

public class MonaLisaNearestNeighborContextFactory {

    public static final int MUTATION_POINT_VARIANCE = 40;
    public static final int NUM_NEIGHBORS_FOR_CLASSIFICATION = 1;
    public static final int NUM_POINTS = 1000;
    public static final int NUM_HYPOTHESES = 1;
    public static final int ALPHA = 230;
    public static final int DOT_RADIUS = 1;
    public static final double CHANCE_TO_MUTATE_POINT = .9;
    public static final int FAKE_PIXEL_SIZE = 4;
    public static final int MUTATION_POINT_COLOR_VARIANCE = 40;

    public ContextImpl getContext() {
        MonaLisa monaLisa = new MonaLisa();
        MonaLisaNearestNeighborAlgorithm algorithm = new MonaLisaNearestNeighborAlgorithm(
                monaLisa,
                NUM_HYPOTHESES,
                NUM_POINTS,
                MUTATION_POINT_VARIANCE,
                NUM_NEIGHBORS_FOR_CLASSIFICATION,
                CHANCE_TO_MUTATE_POINT,
                MUTATION_POINT_COLOR_VARIANCE);
        return new ContextImpl(
                algorithm,
                new DotPainter(
                        new DotProducerBestMonaLisaHypothesis(algorithm),
                        DOT_RADIUS,
                        new MonaLisaAlgorithmPainter(
                                algorithm,
                                ALPHA,
                                new MonaLisaPainter(monaLisa, null),
                                FAKE_PIXEL_SIZE)));
    }
}
