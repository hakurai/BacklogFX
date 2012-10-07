/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package backlogfx.kanban;

import backlog4j.Comment;
import backlog4j.Issue;
import backlogfx.core.ModelFactory;
import com.google.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author eguchi
 */
public class IssueDetailController implements Initializable {

    @Inject
    private ModelFactory modelFactory;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox scrollPaneContent;
    @FXML
    private Parent issueDescription;
    @FXML
    private IssueDescriptionController issueDescriptionController;

    private ObservableList<Comment> commentList = FXCollections.observableArrayList();
    private ObjectProperty<ObservableList<Comment>> commentListProperty
            = new SimpleObjectProperty(commentList);

    private IssueDetailModel model;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model = modelFactory.createModel(IssueDetailModel.class);
    }

    public void loadIsseu(Issue issue) {
        issueDescriptionController.loadIsseu(issue);

//        ReadOnlyObjectProperty<ObservableList<Comment>> comments = model.getComments();
//        commentListProperty.bind(comments);
    }
}
