package backlogfx.kanban;

import backlog4j.Category;
import backlog4j.Issue;
import backlog4j.IssueType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * @author eguchi
 */
public class IssueCell extends BorderPane {

    private Issue issue;
    private Label issueTypeLabel;
    private Label summaryLabel;

    public IssueCell(Issue issue) {
        this.issue = issue;


        setCenter(getSummaryLabel(issue.getSummary()));
        setLeft(getIssueTypeLabel(issue.getIssueType()));

        VBox.setMargin(this, new Insets(3));

        getStyleClass().add("issueCell");
    }

    private Label getSummaryLabel(String summary) {
        if (summaryLabel == null) {
            summaryLabel = new Label(issue.getSummary());
            summaryLabel.setWrapText(true);

            setAlignment(summaryLabel, Pos.TOP_LEFT);
            setPrefHeight(USE_COMPUTED_SIZE);
            setMaxHeight(USE_PREF_SIZE);

            BorderPane.setMargin(summaryLabel, new Insets(5));
        }

        return summaryLabel;
    }

    private Label getIssueTypeLabel(IssueType issueType) {
        if (issueTypeLabel == null) {
            issueTypeLabel = new Label(issueType.getName());
            issueTypeLabel.setStyle("-fx-background-color: " + issueType.getColor() + ";");
            issueTypeLabel.getStyleClass().add("issueType");

        }

        return issueTypeLabel;
    }
}
