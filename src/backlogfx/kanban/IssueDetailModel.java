/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package backlogfx.kanban;

import backlog4j.Comment;
import backlog4j.Issue;
import backlogfx.core.TaskFactory;
import com.google.inject.Inject;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

/**
 * @author eguchi
 */
public class IssueDetailModel {

    @Inject
    private TaskFactory taskFactory;

    private Issue issue;

    public IssueDetailModel() {
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public ReadOnlyObjectProperty<Image> getUserIcon(Integer id) {
        GetUserIconTask task = taskFactory.createTask(GetUserIconTask.class);
        task.setUserId(id);

        task.execute();

        return task.valueProperty();
    }

    public ReadOnlyObjectProperty<ObservableList<Comment>> getComments() {
        GetCommentsTask task = taskFactory.createTask(GetCommentsTask.class);
        task.setIssueId(issue.getId());

        task.execute();

        return task.valueProperty();
    }

}
