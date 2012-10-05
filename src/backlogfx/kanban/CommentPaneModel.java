package backlogfx.kanban;

import backlog4j.Comment;
import backlogfx.BacklogFxContext;
import backlogfx.BacklogFxModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.scene.image.Image;

/**
 * @author eguchi
 */
public class CommentPaneModel {

    @Inject
    private BacklogFxContext context;

    private Comment comment;

    public CommentPaneModel() {
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public ReadOnlyObjectProperty<Image> getCreatedUserIcon() {
        return getUserIcon(comment.getCreatedUser().getId());
    }

    public ReadOnlyObjectProperty<Image> getUserIcon(Integer id) {
        GetUserIconTask task = Guice.createInjector(new BacklogFxModule()).getInstance(GetUserIconTask.class);
        task.setUserId(id);

        context.getThreadPool().execute(task);

        return task.valueProperty();
    }
}
