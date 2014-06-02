package se.jtiden.sudoku.struct;

public interface ListDecorator<T> extends CollectionDecorator<T> {
    int size();
    T get(int i);
}
