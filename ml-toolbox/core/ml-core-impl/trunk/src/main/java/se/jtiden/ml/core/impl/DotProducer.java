package se.jtiden.ml.core.impl;

import se.jtiden.ml.core.api.Point;

import java.util.List;

public interface DotProducer {
    List<? extends Point> getPoints();
}
