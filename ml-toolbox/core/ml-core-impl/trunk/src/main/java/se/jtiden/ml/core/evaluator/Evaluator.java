package se.jtiden.ml.core.evaluator;

public interface Evaluator<T> {
    double getScore(T t);
}
