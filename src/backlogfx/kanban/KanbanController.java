/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package backlogfx.kanban;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author eguchi
 */
public class KanbanController implements Initializable {

    @FXML
    private KanbanColumnController todoColumnController;
    @FXML
    private KanbanColumnController inProgressColumnController;
    @FXML
    private KanbanColumnController resolvedColumnController;
    @FXML
    private KanbanColumnController closedColumnController;
    private KanbanModel model;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void setModel(KanbanModel model) {
        this.model = model;
        todoColumnController.setIssueList(model.getTodoIssues());
        inProgressColumnController.setIssueList(model.getInProgressIssues());
        resolvedColumnController.setIssueList(model.getResolvedIssues());
        closedColumnController.setIssueList(model.getClosedIssues());
    }

    @FXML
    protected void loadIssue(ActionEvent event) {
        model.loadIssue();
    }
}
