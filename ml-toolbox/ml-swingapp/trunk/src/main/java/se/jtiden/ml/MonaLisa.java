package se.jtiden.ml;

import se.jtiden.ml.core.api.Context;
import se.jtiden.ml.core.impl.MonaLisaNearestNeighborContextFactory;

public class MonaLisa extends App {

    protected MonaLisa(Context context) {
        super(context);
    }

    public static void main(String[] args) {
        new MonaLisa(new MonaLisaNearestNeighborContextFactory().getContext());
    }
}
