package se.jtiden.sudoku.struct;

public interface Consumer<T> {
    void apply(final T t);
}
