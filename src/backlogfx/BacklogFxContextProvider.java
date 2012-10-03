package backlogfx;

import backlog4j.BacklogClient;
import backlog4j.BacklogClientFactory;
import backlog4j.User;
import backlog4j.conf.BacklogConfigure;
import backlog4j.conf.BacklogConfigureBuilder;
import com.google.inject.Provider;

import java.io.IOException;

/**
 * @author eguchi
 */
public class BacklogFxContextProvider implements Provider<BacklogFxContext> {

    private final BacklogFxContext context = new BacklogFxContext();

    public BacklogFxContextProvider() {
        BacklogConfigure configure;
        try {
            configure = new BacklogConfigureBuilder().loadPropertyFile("user.properties").buildBacklogConfigure();
            BacklogClient client = new BacklogClientFactory(configure).newBacklogClient();
            context.setClient(client);

            User user = client.getUser().setUserId(configure.getUsername()).execute();
            context.setUser(user);



        } catch (IOException e) {
            e.printStackTrace();
        }





    }

    @Override
    public synchronized BacklogFxContext get() {
        return context;
    }
}
