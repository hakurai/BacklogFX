/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package backlogfx.kanban;

import backlog4j.Category;
import backlog4j.Issue;
import backlog4j.Milestone;
import backlog4j.Resolution;
import backlog4j.User;
import backlog4j.Version;
import backlogfx.BacklogFxModule;
import com.google.inject.Guice;
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
        model = Guice.createInjector(new BacklogFxModule()).getInstance(IssueDescriptionModel.class);
    }


    public void loadIsseu(Issue issue) {
        issueType.setText(issue.getIssueType().getName());
        issueType.setStyle("-fx-background-color: " + issue.getIssueType().getColor() + ";");

        key.setText(issue.getKey());

        createdOn.setText(formatDate("登録日 : ", issue.getCreatedOn()));

        startDate.setText(formatShortDate("開始日 : ", issue.getStartDate()));

        dueDate.setText(formatShortDate("期限日 ; ", issue.getDueDate()));

        categoryValue.setText(formatComponents(issue.getComponents()));

        versionValue.setText(formatVersions(issue.getVersions()));

        milestoneValue.setText(formatMilestones(issue.getMilestones()));

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
        createdUserIcon.imageProperty().bind(model.getUserIcon(createdUser.getId()));

        descriptionBody.getChildren().addAll(createDescriptionBody(issue.getDescription()));


    }

    //20090731
    private String formatShortDate(String prefix, String text) {
        if (text == null || text.isEmpty()) {
            return prefix + "-";
        }
        return prefix + text.substring(0, 4) + "/" + text.substring(4, 6) + "/" + text.substring(6, 8);
    }

    //20090731151859
    private String formatDate(String prefix, String text) {
        if (text == null || text.isEmpty()) {
            return prefix + "-";
        }
        return prefix + text.substring(0, 4) + "/" + text.substring(4, 6) + "/" + text.substring(6, 8) +
                " " + text.substring(8, 10) + ":" + text.substring(10, 12) + ":" + text.substring(12, 14);
    }

    private String formatComponents(List<Category> categories) {
        if (categories.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder(categories.get(0).getName());
        for (int i = 1, len = categories.size(); i < len; i++) {
            sb.append(',').append(categories.get(i).getName());
        }

        return sb.toString();
    }

    private String formatVersions(List<Version> versions) {
        if (versions.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder(versions.get(0).getName());
        for (int i = 1, len = versions.size(); i < len; i++) {
            sb.append(',').append(versions.get(i).getName());
        }

        return sb.toString();
    }

    private String formatMilestones(List<Milestone> milestones) {
        if (milestones.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder(milestones.get(0).getName());
        for (int i = 1, len = milestones.size(); i < len; i++) {
            sb.append(',').append(milestones.get(i).getName());
        }

        return sb.toString();
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
