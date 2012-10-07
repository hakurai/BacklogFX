package backlogfx.core;

import backlog4j.BacklogClient;
import backlog4j.User;
import backlog4j.conf.BacklogConfigure;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author eguchi
 */
public class UserRepository {

    private final BacklogClientProvider clientProvider;
    private final ObservableList<User> userList = FXCollections.observableArrayList();

    public UserRepository(BacklogClientProvider clientProvider) {
        this.clientProvider = clientProvider;
    }

    public User getLoginUser() {
        BacklogClient client = clientProvider.get();
        BacklogConfigure configure = client.getConfigure();
        User user = client.getUser().setUserId(configure.getUsername()).execute();

        return user;
    }
}
