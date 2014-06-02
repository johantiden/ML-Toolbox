package se.jtiden.sudoku.struct;

import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ListDecoratorImpl<T> extends CollectionDecoratorImpl<T> implements ListDecorator<T> {
    public ListDecoratorImpl(final List<T> list) {
        super(list);
    }

    @Override
    public int size() {
        return ((List)getIterable()).size();
    }

    @Override
    public T get(final int i) {
        return ((List<T>) getIterable()).get(i);
    }
}
