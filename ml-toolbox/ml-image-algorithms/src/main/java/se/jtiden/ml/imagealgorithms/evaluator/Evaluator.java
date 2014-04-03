package se.jtiden.ml.imagealgorithms.evaluator;

public interface Evaluator<T> {
    double getScore(T t);
}
