/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package backlogfx;

import backlogfx.core.BacklogFxModule;
import backlogfx.core.InjectFXMLLoader;
import backlogfx.kanban.KanbanController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author eguchi
 */
public class MainViewController implements Initializable {

    @FXML
    private BorderPane topPanel;

    private Parent kanban;
    private KanbanController kanbanController;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            InjectFXMLLoader kanbanLoader = new InjectFXMLLoader(BacklogFxModule.getInstance(), getClass().getResource("kanban/Kanban.fxml"));
            kanban = (Parent) kanbanLoader.load();
            kanbanController = (KanbanController) kanbanLoader.getController();


        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        topPanel.setCenter(kanban);

    }
}
