package backlogfx.kanban;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * @author eguchi
 */
public class IssueCell extends BorderPane {

    private Label summary;

    public IssueCell() {
        summary = new Label();
        summary.setWrapText(true);
        setAlignment(summary, Pos.TOP_LEFT);
        setPrefHeight(USE_COMPUTED_SIZE);
        setMaxHeight(USE_PREF_SIZE);

        setCenter(summary);

        BorderPane.setMargin(summary, new Insets(5));

        VBox.setMargin(this, new Insets(3));

        getStyleClass().add("issueCell");
    }

    public void setSummary(String summary) {
        this.summary.setText(summary);
    }
}
