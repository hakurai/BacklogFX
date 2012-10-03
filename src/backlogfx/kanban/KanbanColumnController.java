/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package backlogfx.kanban;

import backlog4j.Issue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author eguchi
 */
public class KanbanColumnController implements Initializable {

    @FXML
    private VBox body;
    @FXML
    private Label columnName;

    private ObservableList<Issue> issueList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
                        List<? extends Issue> subList = change.getAddedSubList();

                        for (Issue issue : subList) {
                            IssueCell issueCell = new IssueCell();
                            issueCell.setSummary(issue.getSummary());
                            issueCell.prefWidthProperty().bind(body.widthProperty());
                            body.getChildren().add(issueCell);
                        }
                    }
                }


            }
        });

    }
}
