package se.jtiden.sudoku.struct;

public interface CollectionDecorator<T> {
    void forEach(Consumer<T> action);
    <R> R reduce(Collector<T, R> collector);
    boolean anyMatch(Predicate<T> predicate);
    CollectionDecorator<T> filter(Predicate<T> predicate);
}
