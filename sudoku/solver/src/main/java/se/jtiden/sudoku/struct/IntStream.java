package se.jtiden.sudoku.struct;

import java.util.ArrayList;
import java.util.List;

public final class IntStream {
    public static CollectionDecorator<Integer> range(final int startInclusive, final int endExclusive) {
        List<Integer> range = new ArrayList<Integer>(endExclusive-startInclusive);

        for (int i = startInclusive; i < endExclusive; ++i) {
            range.add(i);
        }

        return new CollectionDecoratorImpl<>(range);
    }
}
