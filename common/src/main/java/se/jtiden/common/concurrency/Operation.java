package se.jtiden.common.concurrency;

public interface Operation<T> {
    public void perform(T pParameter);
}
