package se.jtiden.ml.core.impl;


import se.jtiden.ml.core.api.Point;
import se.jtiden.ml.core.impl.nearestneighbor.MonaLisaNearestNeighborAlgorithm;
import se.jtiden.ml.core.impl.nearestneighbor.MonaLisaNearestNeighborHypothesis;

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
