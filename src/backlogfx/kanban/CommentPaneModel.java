package backlogfx.kanban;

import backlog4j.Comment;
import backlogfx.core.TaskFactory;
import com.google.inject.Inject;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.scene.image.Image;

/**
 * @author eguchi
 */
public class CommentPaneModel {

    @Inject
    private TaskFactory taskFactory;
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
        GetUserIconTask task = taskFactory.createTask(GetUserIconTask.class);
        task.setUserId(id);

        task.execute();

        return task.valueProperty();
    }
}
