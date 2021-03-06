package se.jtiden.common.concurrency;

import net.sf.ehcache.util.NamedThreadFactory;

import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Kudos to http://stackoverflow.com/questions/4010185/parallel-for-for-java
 */
public class ParallelPooled<T> implements Parallel<T> {
    private static final int NUM_CORES = Runtime.getRuntime().availableProcessors();

    private static final ExecutorService forPool = Executors.newFixedThreadPool(NUM_CORES * 2, new NamedThreadFactory("Parallel.For"));


    @Override
    public <T> void For(Iterable<T> elements, Operation<T> operation) {
        try {
            // invokeAll blocks for us until all submitted tasks in the call complete
            forPool.invokeAll(createCallables(elements, operation));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static <T> Collection<Callable<Void>> createCallables(Iterable<T> elements, Operation<T> operation) {
        Collection<Callable<Void>> callables = new LinkedList<Callable<Void>>();
        for (T elem : elements) {
            callables.add(new CallOperation(operation, elem));
        }

        return callables;
    }



    private static class CallOperation<T> implements Callable<Void> {
        private final Operation<T> operation;
        private final T elem;

        private CallOperation(Operation<T> operation, T elem) {
            this.operation = operation;
            this.elem = elem;
        }

        @Override
        public Void call() {
            operation.perform(elem);
            return null;
        }
    }
}
