package se.jtiden.ml.core.api;

import java.math.BigInteger;

public interface Hypothesis extends Comparable<Hypothesis> {
    BigInteger valueFunction();
}
