/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package backlogfx.kanban;

import backlog4j.BacklogClient;
import backlog4j.Issue;
import backlog4j.Project;
import com.google.inject.Inject;
import com.google.inject.Provider;
import javafx.concurrent.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * @author eguchi
 */
public class GetIssueTask extends Task<List<Issue>> {

    @Inject
    private BacklogClient client;

    public GetIssueTask() {
    }

    @Override
    protected List<Issue> call() throws Exception {
        List<Project> projects = client.getProjects().execute();

        List<Issue> issueList = new ArrayList<Issue>();


        for (Project project : projects) {
            try {
                List<Issue> issues = client.findIssue().setProjectId(project.getId()).execute();

                issueList.addAll(issues);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return issueList;
    }
}
