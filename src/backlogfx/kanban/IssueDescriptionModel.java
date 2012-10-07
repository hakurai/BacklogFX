package backlogfx.kanban;

import backlog4j.Issue;
import backlogfx.core.TaskFactory;
import com.google.inject.Inject;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.scene.image.Image;

/**
 * @author eguchi
 */
public class IssueDescriptionModel {

    @Inject
    private TaskFactory taskFactory;
    private Issue issue;

    public IssueDescriptionModel() {
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public ReadOnlyObjectProperty<Image> getCreatedUserIcon() {
        return getUserIcon(issue.getCreatedUser().getId());
    }


    public ReadOnlyObjectProperty<Image> getUserIcon(Integer id) {
        GetUserIconTask task = taskFactory.createTask(GetUserIconTask.class);
        task.setUserId(id);

        task.execute();

        return task.valueProperty();
    }

}
