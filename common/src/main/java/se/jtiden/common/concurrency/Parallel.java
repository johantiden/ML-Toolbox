package se.jtiden.common.concurrency;

public interface Parallel<T> {
    <T> void For(Iterable<T> elements, Operation<T> operation);
}
