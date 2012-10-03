/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package backlogfx;

import backlog4j.BacklogClient;
import backlog4j.BacklogClientFactory;
import backlog4j.User;
import backlog4j.conf.BacklogConfigure;
import backlog4j.conf.BacklogConfigureBuilder;
import java.io.IOException;

/**
 *
 * @author eguchi
 */
public class BacklogFxContext {

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
}
