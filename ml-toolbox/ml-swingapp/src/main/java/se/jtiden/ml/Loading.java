package se.jtiden.ml;


import se.jtiden.ml.imagealgorithms.Context;
import se.jtiden.ml.triplecircle.TripleCircleLoadingAnimationContextFactory;

public class Loading extends App {

    protected Loading(Context context) {
        super(context);
    }

    public static void main(String[] args) {
//        new MonaLisa(new MonaLisaNearestNeighborContextFactory().getContext());
//        new MonaLisa(new CirclesContextFactory().getContext());
        new Loading(new TripleCircleLoadingAnimationContextFactory().getContext());
    }
}
