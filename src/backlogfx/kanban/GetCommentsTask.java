package backlogfx.kanban;

import backlog4j.BacklogClient;
import backlog4j.Comment;
import backlogfx.core.BacklogClientProvider;
import backlogfx.core.BacklogTaskBase;
import com.google.inject.Inject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * @author eguchi
 */
public class GetCommentsTask extends BacklogTaskBase<ObservableList<Comment>> {

    @Inject
    private BacklogClientProvider backlogClientProvider;

    private Integer issueId;

    public void setIssueId(Integer issueId) {
        this.issueId = issueId;
    }

    @Override
    protected ObservableList<Comment> call() throws Exception {
        BacklogClient client = backlogClientProvider.get();

        List<Comment> list = client.getComments().setIssueId(issueId).execute();

        return FXCollections.observableArrayList(list);
    }
}
