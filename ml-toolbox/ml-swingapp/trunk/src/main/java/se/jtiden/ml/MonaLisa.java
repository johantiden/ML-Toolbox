package se.jtiden.ml;

import se.jtiden.ml.core.api.Context;
import se.jtiden.ml.core.impl.circlesadditive.AdditiveContextFactory;
import se.jtiden.ml.core.impl.original.OriginalContextFactory;

public class MonaLisa extends App {

    protected MonaLisa(Context context) {
        super(context);
    }

    public static void main(String[] args) {
//        new MonaLisa(new MonaLisaNearestNeighborContextFactory().getContext());
//        new MonaLisa(new CirclesContextFactory().getContext());
        new MonaLisa(new OriginalContextFactory().getContext());
        new MonaLisa(new AdditiveContextFactory().getContext());
    }
}
