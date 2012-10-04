/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package backlogfx.kanban;

import backlog4j.BacklogClient;
import backlog4j.UserIcon;
import backlogfx.BacklogFxContext;
import com.google.inject.Inject;
import javafx.concurrent.Task;
import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;

/**
 * @author eguchi
 */
public class GetUserIconTask extends Task<Image> {

    @Inject
    private BacklogFxContext context;

    private Integer userId;

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    protected Image call() throws Exception {
        BacklogClient client = context.getClient();

        UserIcon userIcon = client.getUserIcon().setId(userId).execute();

        return new Image(new ByteArrayInputStream(userIcon.getData()));
    }

}
