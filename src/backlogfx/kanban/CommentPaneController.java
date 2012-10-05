/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package backlogfx.kanban;

import backlog4j.Comment;
import backlogfx.BacklogFxModule;
import backlogfx.StringFormatUtil;
import com.google.inject.Guice;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author eguchi
 */
public class CommentPaneController implements Initializable {
    @FXML
    private Label createdOn;
    @FXML
    private ImageView createdUserIcon;
    @FXML
    private Label createdUserValue;
    @FXML
    private VBox commnetBody;

    private CommentPaneModel model;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void setCommnet(Comment commnet) {
        model = Guice.createInjector(new BacklogFxModule()).getInstance(CommentPaneModel.class);
        model.setComment(commnet);

        createdUserIcon.imageProperty().bind(model.getCreatedUserIcon());

        createdOn.setText(StringFormatUtil.formatDate("", commnet.getCreatedOn()));

        createdUserValue.setText(commnet.getCreatedUser().getName());
    }


}
