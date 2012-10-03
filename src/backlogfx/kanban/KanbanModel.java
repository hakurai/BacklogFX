/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package backlogfx.kanban;

import backlog4j.Issue;
import backlog4j.Status;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

import java.util.List;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;

/**
 * @author eguchi
 */
public class KanbanModel {

    private GetIssueService getIssueService = new GetIssueService();
    private ObservableList<Issue> todoIssues = FXCollections.observableArrayList();
    private ObservableList<Issue> inProgressIssues = FXCollections.observableArrayList();
    private ObservableList<Issue> resolvedIssues = FXCollections.observableArrayList();
    private ObservableList<Issue> closedIssues = FXCollections.observableArrayList();

    public KanbanModel() {
        getIssueService.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {

                List<Issue> issues = (List<Issue>) workerStateEvent.getSource().getValue();
                for (Issue issue : issues) {
                    Status status = issue.getStatus();

                    switch (status.getId()) {
                        case 1:
                            todoIssues.add(issue);
                            break;
                        case 2:
                            inProgressIssues.add(issue);
                            break;
                        case 3:
                            resolvedIssues.add(issue);
                            break;
                        case 4:
                            closedIssues.add(issue);
                            break;
                        default:
                            throw new IllegalStateException("unknown id : " + issue.getId());
                    }
                }


            }
        });
    }

    public void init() {
        getIssueService.start();
    }

    public ObservableList<Issue> getTodoIssues() {
        return todoIssues;
    }

    public ObservableList<Issue> getInProgressIssues() {
        return inProgressIssues;
    }

    public ObservableList<Issue> getResolvedIssues() {
        return resolvedIssues;
    }

    public ObservableList<Issue> getClosedIssues() {
        return closedIssues;
    }

    public final ReadOnlyDoubleProperty progressProperty() {
        return getIssueService.progressProperty();
    }

    public final ReadOnlyBooleanProperty runningProperty() {
        return getIssueService.runningProperty();
    }

    public void loadIssue() {
        getIssueService.restart();


    }
}
