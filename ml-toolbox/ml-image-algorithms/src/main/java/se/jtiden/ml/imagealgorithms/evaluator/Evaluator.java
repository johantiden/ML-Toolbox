package se.jtiden.ml.imagealgorithms.evaluator;

import java.io.Serializable;

public interface Evaluator<T> extends Serializable {
    double getScore(T t);

    T getTarget();

    int getWidth();

    int getHeight();
}
