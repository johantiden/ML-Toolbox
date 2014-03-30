package se.jtiden.ml.core.api;

public interface Evaluator<T> {
    double getScore(T t);
}
