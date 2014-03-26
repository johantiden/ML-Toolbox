package se.jtiden.ml.core.api;


public interface Context {
    AlgorithmStepPainter getPainter();
    IterativeAlgorithm getAlgorithm();
}
