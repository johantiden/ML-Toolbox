package se.jtiden.ml.core;

import se.jtiden.ml.core.algorithm.api.Hypothesis;
import se.jtiden.ml.core.algorithm.api.IterativeAlgorithm;
import se.jtiden.ml.core.painter.AlgorithmStepPainter;
import se.jtiden.ml.core.evaluator.Evaluator;
import se.jtiden.ml.core.painter.HypothesisPainterFactory;

import java.awt.*;

public class OriginalContextFactory {

    public static final double SCALE_DOWN_BEFORE = 1;
    public static final double SCALE_UP_AFTER = 2;

    public ContextImpl getContext() {
        final MonaLisa monaLisa = new MonaLisa(SCALE_DOWN_BEFORE);

        final AlgorithmStepPainter painter = new AlgorithmStepPainter() {
            @Override
            public void paint() {
            }

            @Override
            public Image getImage() {
                return monaLisa.getImage().asImage();
            }

            @Override
            public int getWidth() {
                return monaLisa.getWidth();
            }

            @Override
            public int getHeight() {
                return monaLisa.getHeight();
            }
        };
        return new ContextImpl(
                new IterativeAlgorithm() {
                    @Override
                    public void iterate() {

                    }

                    @Override
                    public Hypothesis getBestHypothesis() {
                        return new Hypothesis() {
                            @Override
                            public double valueFunction() {
                                return 0;
                            }

                            @Override
                            public int compareTo(Hypothesis o) {
                                return 0;
                            }
                        };
                    }

                    @Override
                    public Evaluator getEvaluator() {
                        return new Evaluator() {
                            @Override
                            public double getScore(Object o) {
                                return 0;
                            }
                        };
                    }
                },
                painter
                ,
                new HypothesisPainterFactory() {
                    @Override
                    public AlgorithmStepPainter create(Hypothesis hypothesis) {
                        return painter;
                    }

                    @Override
                    public int getWidth() {
                        return monaLisa.getWidth();
                    }

                    @Override
                    public int getHeight() {
                        return  monaLisa.getHeight();
                    }
                },
                SCALE_UP_AFTER);
    }
}
