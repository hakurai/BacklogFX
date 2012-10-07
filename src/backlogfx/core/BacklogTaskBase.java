package backlogfx.core;

import javafx.concurrent.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author eguchi
 */
public abstract class BacklogTaskBase<T> extends Task<T> {

    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(3);

    protected static ExecutorService getExecutorService() {
        return EXECUTOR_SERVICE;
    }

    public static void shutdown() {
        EXECUTOR_SERVICE.shutdown();
        try {
            EXECUTOR_SERVICE.awaitTermination(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            EXECUTOR_SERVICE.shutdownNow();
        }
    }


    public void execute() {
        EXECUTOR_SERVICE.execute(this);
    }
}
