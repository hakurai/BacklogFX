/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package backlogfx.kanban;

import backlog4j.Issue;
import backlogfx.MainViewController;
import backlogfx.core.BacklogFxModule;
import backlogfx.core.InjectFXMLLoader;
import backlogfx.core.ModelFactory;
import com.google.inject.Inject;
import com.google.inject.Provider;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author eguchi
 */
public class KanbanController implements Initializable {

    private ModelFactory modelFactory;

    private MainViewController mainViewController;
    @FXML
    private Parent kanbanBody;
    @FXML
    private Button refreshButton;
    @FXML
    private Region veil;
    @FXML
    private ProgressIndicator progress;
    @FXML
    private ChoiceBox<String> selectUser;
    @FXML
    private Parent todoColumn;
    @FXML
    private KanbanColumnController todoColumnController;
    @FXML
    private Parent inProgressColumn;
    @FXML
    private KanbanColumnController inProgressColumnController;
    @FXML
    private Parent resolvedColumn;
    @FXML
    private KanbanColumnController resolvedColumnController;
    @FXML
    private Parent closedColumn;
    @FXML
    private KanbanColumnController closedColumnController;
    private KanbanModel model;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        kanbanBody.getStylesheets().add(getClass().getResource("kanban.css").toExternalForm());
        todoColumn.getStyleClass().add("todoColumn");
        inProgressColumn.getStyleClass().add("inProgressColumn");
        resolvedColumn.getStyleClass().add("resolvedColumn");
        closedColumn.getStyleClass().add("closedColumn");

        todoColumnController.setKanbanController(this);
        inProgressColumnController.setKanbanController(this);
        resolvedColumnController.setKanbanController(this);
        closedColumnController.setKanbanController(this);

        todoColumnController.setColumnName("未対応");
        todoColumnController.setIssueList(model.getTodoIssues());

        inProgressColumnController.setColumnName("処理中");
        inProgressColumnController.setIssueList(model.getInProgressIssues());

        resolvedColumnController.setColumnName("処理済み");
        resolvedColumnController.setIssueList(model.getResolvedIssues());

        closedColumnController.setColumnName("完了");
        closedColumnController.setIssueList(model.getClosedIssues());

        refreshButton.disableProperty().bind(model.runningProperty());
        veil.visibleProperty().bind(model.runningProperty());
        progress.visibleProperty().bind(model.runningProperty());
        progress.progressProperty().bind(model.progressProperty());

        model.init();
    }

    @Inject
    public void setModelFactory(Provider<ModelFactory> provider) {
        this.modelFactory = provider.get();
        this.model = modelFactory.createModel(KanbanModel.class);


    }

    @FXML
    public void refresh(ActionEvent event) {
        model.refresh();
    }

    public void showIssue(Issue issue) {
        try {
            InjectFXMLLoader kanbanLoader = new InjectFXMLLoader(BacklogFxModule.getInstance(), getClass().getResource("IssueDetail.fxml"));
            Parent parent = (Parent) kanbanLoader.load();
            IssueDetailController controller = (IssueDetailController) kanbanLoader.getController();

            parent.getStylesheets().add(getClass().getResource("issueDescription.css").toExternalForm());

            final Stage stage = new Stage();
            controller.loadIsseu(issue);
            Scene scene = new Scene(parent);

            stage.setTitle(issue.getKey() + " " + issue.getSummary());
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
}
