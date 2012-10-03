/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package backlogfx;

import backlog4j.BacklogClient;
import backlog4j.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author eguchi
 */
public class BacklogFxContext {

    private final ExecutorService threadPool = Executors.newFixedThreadPool(5);
    private BacklogClient client;
    private User user;

    public BacklogFxContext() {
    }

    public synchronized void setClient(BacklogClient client) {
        this.client = client;
    }

    public synchronized BacklogClient getClient() {
        return client;
    }

    public synchronized User getUser() {
        return user;
    }

    public synchronized void setUser(User user) {
        this.user = user;
    }

    public ExecutorService getThreadPool() {
        return threadPool;
    }


    public void destroy() {
        threadPool.shutdown();
        try {
            threadPool.awaitTermination(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            threadPool.shutdownNow();
        }
    }
}
