package se.jtiden.ml;


import se.jtiden.ml.imagealgorithms.Context;
import se.jtiden.ml.imagealgorithms.circles.CirclesContextFactory;
import se.jtiden.ml.imagealgorithms.circlesadditive.AdditiveContextFactory;
import se.jtiden.ml.imagealgorithms.nearestneighbor.MonaLisaNearestNeighborContextFactory;
import se.jtiden.ml.imagealgorithms.original.OriginalContextFactory;

public class MonaLisa extends App {

    protected MonaLisa(Context context) {
        super(context);
    }

    public static void main(String[] args) {
        new MonaLisa(new MonaLisaNearestNeighborContextFactory().getContext());
        new MonaLisa(new CirclesContextFactory().getContext());
        new MonaLisa(new OriginalContextFactory().getContext());
        new MonaLisa(new AdditiveContextFactory().getContext());
    }
}
