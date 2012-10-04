/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package backlogfx.kanban;

import backlog4j.Issue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.WeakHashMap;

/**
 * FXML Controller class
 *
 * @author eguchi
 */
public class KanbanColumnController implements Initializable {

    private KanbanController kanbanController;
    @FXML
    private VBox body;
    @FXML
    private Label columnName;
    private ObservableList<Issue> issueList;
    private Map<Issue,IssueCell> cellMap = new WeakHashMap<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setKanbanController(KanbanController kanbanController) {
        this.kanbanController = kanbanController;
    }

    public String getColumnName() {
        return columnName.getText();
    }

    public void setColumnName(String columnName) {
        this.columnName.setText(columnName);
    }

    public void setIssueList(ObservableList<Issue> issueList) {
        this.issueList = issueList;

        issueList.addListener(new ListChangeListener<Issue>() {
            @Override
            public void onChanged(Change<? extends Issue> change) {
                while (change.next()) {
                    if (change.wasPermutated()) {
                    } else if (change.wasUpdated()) {
                    } else {
                        for (final Issue issue : change.getRemoved()) {
                            removeIssue(issue);
                        }

                        for (final Issue issue : change.getAddedSubList()) {
                            addIssue(issue);
                        }
                    }
                }
            }
        });

    }

    private void addIssue(final Issue issue) {
        IssueCell issueCell = new IssueCell(issue);
        issueCell.prefWidthProperty().bind(body.widthProperty());
        issueCell.setOnMouseClicked(new EventHandler<MouseEvent>() {
            private Issue myIssue = issue;

            @Override
            public void handle(MouseEvent mouseEvent) {
                kanbanController.showIssue(myIssue);
            }
        });
        body.getChildren().add(issueCell);
        
        cellMap.put(issue, issueCell);
    }

    private void removeIssue(Issue issue) {
        
        body.getChildren().remove(cellMap.get(issue));


    }
}
