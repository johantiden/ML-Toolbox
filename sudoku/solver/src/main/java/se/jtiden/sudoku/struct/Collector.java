package se.jtiden.sudoku.struct;

public interface Collector<T, R> {
    void collect(T t);
    R get();
}
