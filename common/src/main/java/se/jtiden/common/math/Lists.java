package se.jtiden.common.math;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class Lists implements Serializable {
    private static final long serialVersionUID = -2802765472797927784L;

    public static Iterable<Integer> range(int maxExclusive) {
        Collection<Integer> range = new ArrayList<Integer>(maxExclusive);
        for (int i = 0; i < maxExclusive; ++i) {
            range.add(i);
        }
        return range;
    }
}