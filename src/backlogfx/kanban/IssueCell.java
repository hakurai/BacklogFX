package backlogfx.kanban;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 * @author eguchi
 */
public class IssueCell extends BorderPane{

    private Label summary;

    public IssueCell() {
        summary = new Label();
        summary.setWrapText(true);
        setAlignment(summary, Pos.TOP_LEFT);
        setPrefHeight(USE_COMPUTED_SIZE);
        setMaxHeight(USE_PREF_SIZE);

        setCenter(summary);
        
        getStylesheets().add("IssueCell.css");
        getStyleClass().add("issueCell");
    }

    public void setSummary(String summary) {
        this.summary.setText(summary);
    }
}
