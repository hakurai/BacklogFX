/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package backlogfx.kanban;

import backlog4j.Comment;
import backlog4j.Issue;
import backlogfx.BacklogFxContext;
import backlogfx.BacklogFxModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

/**
 * @author eguchi
 */
public class IssueDetailModel {

    @Inject
    private BacklogFxContext context;

    private Issue issue;

    public IssueDetailModel() {
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public ReadOnlyObjectProperty<Image> getUserIcon(Integer id) {
        GetUserIconTask task = Guice.createInjector(new BacklogFxModule()).getInstance(GetUserIconTask.class);
        task.setUserId(id);

        context.getThreadPool().execute(task);

        return task.valueProperty();
    }

    public ReadOnlyObjectProperty<ObservableList<Comment>> getComments() {
        GetCommentsTask task = Guice.createInjector(new BacklogFxModule()).getInstance(GetCommentsTask.class);
        task.setIssueId(issue.getId());

        context.getThreadPool().execute(task);

        return task.valueProperty();
    }

}
