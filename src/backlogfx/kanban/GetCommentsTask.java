package backlogfx.kanban;

import backlog4j.BacklogClient;
import backlog4j.Comment;
import backlogfx.BacklogFxContext;
import com.google.inject.Inject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

import java.util.List;

/**
 * @author eguchi
 */
public class GetCommentsTask extends Task<ObservableList<Comment>> {

    @Inject
    private BacklogFxContext context;

    private Integer issueId;

    public void setIssueId(Integer issueId) {
        this.issueId = issueId;
    }

    @Override
    protected ObservableList<Comment> call() throws Exception {
        BacklogClient client = context.getClient();

        List<Comment> list = client.getComments().setIssueId(issueId).execute();

        return FXCollections.observableArrayList(list);
    }
}
