package se.jtiden.sudoku.struct;

import java.util.ArrayList;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class CollectionDecoratorImpl<T> implements CollectionDecorator<T> {

    private final Iterable<T> iterable;

    public CollectionDecoratorImpl(final Iterable<T> iterable) {
        this.iterable = iterable;
    }

    @Override
    public void forEach(final Consumer<T> action) {
        for(T t : iterable) {
            action.apply(t);
        }
    }

    @Override
    public <R> R reduce(final Collector<T, R> collector) {
        forEach(new Consumer<T>() {
            @Override
            public void apply(final T t) {
                collector.collect(t);
            }
        });

        return collector.get();
    }

    @Override
    public boolean anyMatch(final Predicate<T> predicate) {
        return reduce(new Collector<T, Boolean>() {
            public boolean match = false;

            @Override
            public void collect(final T t) {
                if (predicate.evaluate(t)) {
                    match = true;
                }
            }

            @Override
            public Boolean get() {
                return match;
            }
        });
    }

    @Override
    public CollectionDecorator<T> filter(final Predicate<T> predicate) {
        final List<T> filtered = new ArrayList<T>();

        forEach(new Consumer<T>() {
            @Override
            public void apply(final T t) {
                if (predicate.evaluate(t)) {
                    filtered.add(t);
                }
            }
        });
        return new CollectionDecoratorImpl<>(filtered);
    }

    protected Iterable<T> getIterable() {
        return iterable;
    }
}
