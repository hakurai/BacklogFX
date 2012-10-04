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
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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

        List<Project> projects = client.getProjects().execute();
        final int projectCount = projects.size();

        final List<Issue> issueList = new ArrayList<>();

        List<SubGetIssueTask> taskList = new ArrayList<>(projectCount);
        final AtomicInteger count = new AtomicInteger(0);
        for (Project project : projects) {
            SubGetIssueTask task = new SubGetIssueTask(project);
            task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                @Override
                public void handle(WorkerStateEvent e) {
                    List<Issue> issues = (List<Issue>) e.getSource().getValue();
                    issueList.addAll(issues);
                    updateProgress(count.incrementAndGet(), projectCount);
                }
            });

            context.getThreadPool().execute(task);
            taskList.add(task);
        }

        for (SubGetIssueTask task : taskList) {
            try {
                task.get();
            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
        }


        return issueList;
    }

    private class SubGetIssueTask extends Task<List<Issue>> {

        private final Project project;

        private SubGetIssueTask(Project project) {
            this.project = project;
        }

        @Override
        protected List<Issue> call() throws Exception {
            BacklogClient client = context.getClient();
            User user = context.getUser();

            List<Issue> issues =
                    client.findIssue()
                            .setProjectId(project.getId())
                            .addAssignerId(user.getId())
                            .execute();

            return issues;
        }


    }


}
