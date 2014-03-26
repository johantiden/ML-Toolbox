package se.jtiden.ml.core.impl;


import se.jtiden.ml.core.api.Point;

import java.util.List;

public class DotProducerBestMonaLisaHypothesis implements DotProducer {

    private final MonaLisaNearestNeighborAlgorithm algorithm;

    public DotProducerBestMonaLisaHypothesis(MonaLisaNearestNeighborAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public List<? extends Point> getPoints() {
        MonaLisaNearestNeighborHypothesis hypothesis = algorithm.getBestHypothesis();
        return hypothesis.getPoints();
    }
}
