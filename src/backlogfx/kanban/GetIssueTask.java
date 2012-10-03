/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package backlogfx.kanban;

import backlog4j.BacklogClient;
import backlog4j.Issue;
import backlog4j.Project;
import backlog4j.User;
import backlogfx.BacklogFxContext;
import com.google.inject.Inject;
import javafx.concurrent.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * @author eguchi
 */
public class GetIssueTask extends Task<List<Issue>> {

    @Inject
    private BacklogFxContext context;

    public GetIssueTask() {
    }

    @Override
    protected List<Issue> call() throws Exception {
        updateProgress(0, 100);

        BacklogClient client = context.getClient();
        User user = context.getUser();

        List<Project> projects = client.getProjects().execute();
        int projectCount = projects.size();

        List<Issue> issueList = new ArrayList<>();

        int i = 0;
        for (Project project : projects) {
            try {
                List<Issue> issues =
                        client.findIssue()
                                .setProjectId(project.getId())
                                .addAssignerId(user.getId())
                                .execute();

                issueList.addAll(issues);

                i++;
                updateProgress(i, projectCount);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return issueList;
    }
}
