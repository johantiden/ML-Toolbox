package se.jtiden.sudoku.struct;

import se.jtiden.sudoku.domain.Node;

public interface Predicate<T> {
    boolean evaluate(T t);
}
