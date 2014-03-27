package se.jtiden.ml;

import se.jtiden.ml.core.api.Context;
import se.jtiden.ml.core.impl.circles.MonaLisaCirclesContextFactory;
import se.jtiden.ml.core.impl.nearestneighbor.MonaLisaNearestNeighborContextFactory;

public class MonaLisa extends App {

    protected MonaLisa(Context context) {
        super(context);
    }

    public static void main(String[] args) {
//        new MonaLisa(new MonaLisaNearestNeighborContextFactory().getContext());
        new MonaLisa(new MonaLisaCirclesContextFactory().getContext());
    }
}
