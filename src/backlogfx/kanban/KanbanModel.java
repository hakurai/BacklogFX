/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package backlogfx.kanban;

import backlog4j.Issue;
import backlog4j.Status;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

import java.util.List;

/**
 * @author eguchi
 */
public class KanbanModel {

    private GetIssueService getIssueService = new GetIssueService();
    private final ObservableList<Issue> todoIssues = FXCollections.observableArrayList();
    private final ObservableList<Issue> inProgressIssues = FXCollections.observableArrayList();
    private final ObservableList<Issue> resolvedIssues = FXCollections.observableArrayList();
    private final ObservableList<Issue> closedIssues = FXCollections.observableArrayList();

    private ReadOnlyObjectProperty<Issue> selectedIssue = new SimpleObjectProperty<>();

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

    public void refresh() {
        todoIssues.clear();
        inProgressIssues.clear();
        resolvedIssues.clear();
        closedIssues.clear();

        getIssueService.restart();
    }
}
