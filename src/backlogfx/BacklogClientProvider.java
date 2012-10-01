package backlogfx;

import backlog4j.BacklogClient;
import backlog4j.BacklogClientFactory;
import backlog4j.conf.BacklogConfigure;
import backlog4j.conf.BacklogConfigureBuilder;
import com.google.inject.Provider;

import java.io.IOException;

/**
 * @author eguchi
 */
public class BacklogClientProvider implements Provider<BacklogClient> {

    private BacklogClient client;

    public BacklogClientProvider() {
        BacklogConfigure configure;
        try {
            configure = new BacklogConfigureBuilder().loadPropertyFile("user.properties").buildBacklogConfigure();
            setClient( new BacklogClientFactory(configure).newBacklogClient() );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void setClient(BacklogClient client) {
        this.client = client;
    }

    @Override
    public synchronized BacklogClient get() {
        return client;
    }
}
