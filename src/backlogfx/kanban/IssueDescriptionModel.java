package backlogfx.kanban;

import backlog4j.Issue;
import backlogfx.BacklogFxContext;
import backlogfx.BacklogFxModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.scene.image.Image;

/**
 * @author eguchi
 */
public class IssueDescriptionModel {

    @Inject
    private BacklogFxContext context;

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
        GetUserIconTask task = Guice.createInjector(new BacklogFxModule()).getInstance(GetUserIconTask.class);
        task.setUserId(id);

        context.getThreadPool().execute(task);

        return task.valueProperty();
    }

}
