package backlogfx.core;

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
            BacklogClient client = new BacklogClientFactory(configure).newBacklogClient();
            this.client = client;

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public BacklogClient get() {
        return client;
    }
}
