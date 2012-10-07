/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package backlogfx.kanban;

import backlog4j.Issue;
import backlog4j.Resolution;
import backlog4j.User;
import backlogfx.core.ModelFactory;
import backlogfx.util.StringFormatUtil;
import com.google.inject.Inject;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author eguchi
 */
public class IssueDescriptionController implements Initializable {

    @Inject
    private ModelFactory modelFactory;
    private IssueDescriptionModel model;

    @FXML
    private Label issueType;
    @FXML
    private Label key;
    @FXML
    private Insets x1;
    @FXML
    private Label createdOn;
    @FXML
    private Label startDate;
    @FXML
    private Label dueDate;
    @FXML
    private Insets x2;
    @FXML
    private Label summary;
    @FXML
    private Insets x3;
    @FXML
    private Label issueTypeValue;
    @FXML
    private Label categoryValue;
    @FXML
    private Label versionValue;
    @FXML
    private Label milestoneValue;
    @FXML
    private Label estimatedOursValue;
    @FXML
    private Label priorityValue;
    @FXML
    private Label resolutionValue;
    @FXML
    private Label statusValue;
    @FXML
    private Label assignerValue;
    @FXML
    private Label actualHoursValue;
    @FXML
    private ImageView createdUserIcon;
    @FXML
    private Label createdUserName;
    @FXML
    private VBox descriptionBody;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }


    public void loadIsseu(Issue issue) {

        model = modelFactory.createModel(IssueDescriptionModel.class);
        model.setIssue(issue);

        createdUserIcon.imageProperty().bind(model.getCreatedUserIcon());

        issueType.setText(issue.getIssueType().getName());
        issueType.setStyle("-fx-background-color: " + issue.getIssueType().getColor() + ";");

        key.setText(issue.getKey());

        createdOn.setText(StringFormatUtil.formatDate("登録日 : ", issue.getCreatedOn()));

        startDate.setText(StringFormatUtil.formatShortDate("開始日 : ", issue.getStartDate()));

        dueDate.setText(StringFormatUtil.formatShortDate("期限日 : ", issue.getDueDate()));

        categoryValue.setText(StringFormatUtil.formatComponents(issue.getComponents()));

        versionValue.setText(StringFormatUtil.formatVersions(issue.getVersions()));

        milestoneValue.setText(StringFormatUtil.formatMilestones(issue.getMilestones()));

        Double estimatedHours = issue.getEstimatedHours();
        if (estimatedHours != null) {
            estimatedOursValue.setText(estimatedHours.toString());
        }

        Double actualHours = issue.getActualHours();
        if (actualHours != null) {
            actualHoursValue.setText(actualHours.toString());
        }

        priorityValue.setText(issue.getPriority().getName());

        Resolution resolution = issue.getResolution();
        if (resolution != null) {
            resolutionValue.setText(resolution.getName());
        }

        statusValue.setText(issue.getStatus().getName());

        summary.setText(issue.getSummary());

        User assigner = issue.getAssigner();
        if (assigner != null) {
            assignerValue.setText(assigner.getName());
        }

        User createdUser = issue.getCreatedUser();
        createdUserName.setText(createdUser.getName());

        descriptionBody.getChildren().addAll(createDescriptionBody(issue.getDescription()));


    }


    private List<Node> createDescriptionBody(String description) {
        ArrayList<Node> nodes = new ArrayList<>();

        Label label = new Label(description);
        label.setMaxWidth(Label.USE_PREF_SIZE);
        label.setMinHeight(Label.USE_PREF_SIZE);
        label.setWrapText(true);
        nodes.add(label);

        return nodes;
    }
}
