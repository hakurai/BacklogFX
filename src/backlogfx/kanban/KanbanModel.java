/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package backlogfx.kanban;

import backlog4j.Issue;
import backlog4j.Status;
import backlogfx.BacklogFxContext;
import com.google.inject.Inject;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

import java.util.List;

/**
 * @author eguchi
 */
public class KanbanModel {

    @Inject
    private BacklogFxContext context;
    private GetIssueService getIssueService = new GetIssueService();
    private final ObservableList<Issue> allIssues = FXCollections.observableArrayList();
    private final ObservableList<Issue> todoIssues = FXCollections.observableArrayList();
    private final ObservableList<Issue> inProgressIssues = FXCollections.observableArrayList();
    private final ObservableList<Issue> resolvedIssues = FXCollections.observableArrayList();
    private final ObservableList<Issue> closedIssues = FXCollections.observableArrayList();

    public KanbanModel() {
        getIssueService.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                List<Issue> issues = (List<Issue>) workerStateEvent.getSource().getValue();

                for (Issue issue : issues) {
                    if (allIssues.contains(issue)) {
                        allIssues.remove(issue);
                    }
                    allIssues.add(issue);
                }
            }
        });

        allIssues.addListener(new ListChangeListener<Issue>() {
            @Override
            public void onChanged(Change<? extends Issue> change) {
                while (change.next()) {
                    if (change.wasRemoved()) {
                        for (Issue issue : change.getRemoved()) {
                            removeIssue(issue);
                        }

                    } else if (change.wasAdded()) {
                        for (Issue issue : change.getAddedSubList()) {
                            addIssue(issue);
                        }
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
        getIssueService.restart();
    }

    private void addIssue(Issue issue) {
        ObservableList<Issue> list = getBelongList(issue.getStatus());

        list.add(issue);
    }

    private void removeIssue(Issue issue) {
        ObservableList<Issue> list = getBelongList(issue.getStatus());

        list.remove(issue);

    }

    private ObservableList<Issue> getBelongList(Status status) {
        switch (status.getId()) {
            case 1:
                return todoIssues;

            case 2:
                return inProgressIssues;

            case 3:
                return resolvedIssues;

            case 4:
                return closedIssues;

            default:
                throw new IllegalStateException("unknown id : " + status.getId());
        }
    }

}
