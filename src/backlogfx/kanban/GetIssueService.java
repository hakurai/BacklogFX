/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package backlogfx.kanban;

import backlog4j.Issue;
import backlogfx.core.TaskFactory;
import com.google.inject.Inject;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.util.List;

/**
 * @author eguchi
 */
public class GetIssueService extends Service<List<Issue>> {

    @Inject
    private TaskFactory taskFactory;

    @Override
    protected Task<List<Issue>> createTask() {
        return taskFactory.createTask(GetIssueTask.class);
    }

}
