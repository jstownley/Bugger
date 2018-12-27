package com.jonathantownley.bugger.controller;

import com.jonathantownley.bugger.Bugger;
import com.jonathantownley.bugger.model.Repository;
import com.jonathantownley.bugger.service.RepositoryService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.util.Optional;

@Controller
public class NewRepository {

    @Autowired
    private RepositoryService repositoryService;

    @FXML private TextField newRepoName;
    @FXML private Label newRepoLocation;
    @FXML private Button repoBrowseButton;
    @FXML private Button saveButton;
    @FXML private Button cancelButton;

    @FXML
    private void initialize() {
        // Set listener on text field
        newRepoName.setOnAction(event -> {
            enableSave();
        });

        // Set onActions for buttons
        repoBrowseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DirectoryChooser directoryChooser = new DirectoryChooser();
                directoryChooser.setTitle("Select Some Directories");
                directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));

                File dir = directoryChooser.showDialog(Bugger.newRepoStage);
                if (null != dir) {
                    newRepoLocation.setText(dir.getAbsolutePath());
                }
                enableSave();
            }
        });

        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirm Add Repository");
                alert.setHeaderText("");
                alert.setContentText("Bugger will need to be restarted before bugs " +
                    "or products can be added to the new repository");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    repositoryService.addRepository(new Repository(newRepoName.getText(),
                        newRepoLocation.getText() + File.separator + newRepoName.getText()));
                }
                Stage stage = (Stage) saveButton.getScene().getWindow();
                stage.hide();
            }
        });

        cancelButton.setCancelButton(true);
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.hide();
            }
        });
    }

    private void enableSave() {
        if (null != newRepoName && null != newRepoLocation) {
            saveButton.setDisable(false);
        }
        else {
            saveButton.setDisable(true);
        }
    }

}
