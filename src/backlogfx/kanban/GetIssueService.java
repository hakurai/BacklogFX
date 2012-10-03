/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package backlogfx.kanban;

import backlog4j.Issue;
import backlogfx.BacklogFxModule;
import com.google.inject.Guice;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.util.List;

/**
 * @author eguchi
 */
public class GetIssueService extends Service<List<Issue>> {

    @Override
    protected Task<List<Issue>> createTask() {
        return Guice.createInjector(new BacklogFxModule()).getInstance(GetIssueTask.class);
    }

}
