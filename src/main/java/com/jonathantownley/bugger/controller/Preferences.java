package com.jonathantownley.bugger.controller;

import com.jonathantownley.bugger.model.Bug;
import com.jonathantownley.bugger.model.Repository;
import com.jonathantownley.bugger.service.BugService;
import com.jonathantownley.bugger.service.PreferenceService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class Preferences {

    @Autowired
    private PreferenceService preferenceService;

    @Autowired
    private BugService bugService;

    @Autowired
    private List<String> repositoryNames;

    @FXML private TextField authorName;
    @FXML private CheckBox showClosed;
    @FXML private CheckBox showRejected;
    @FXML private CheckBox showDuplicates;
    @FXML private Button saveButton;
    @FXML private Button cancelButton;

    @FXML
    private void initialize() {
        // Initialize the values displayed
        authorName.setText(preferenceService.getAuthor());
        showClosed.setSelected(preferenceService.getShowClosed());
        showRejected.setSelected(preferenceService.getShowRejected());
        showDuplicates.setSelected(preferenceService.getShowDuplicates());

        // Set event listeners
        cancelButton.setCancelButton(true);
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.hide();
            }
        });

        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Set checkbox preferences
                preferenceService.setShowClosed(showClosed.isSelected());
                preferenceService.setShowClosed(showClosed.isSelected());
                preferenceService.setShowClosed(showClosed.isSelected());

                // Check name change and ask the user whether to change name on all bugs
                String newName = authorName.getCharacters().toString();
                String oldName = preferenceService.getAuthor();
                if (!newName.equals(oldName)) {
                    // Launch the dialog
                    preferenceService.setAuthor(newName);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Update bug authors?");
                    alert.setHeaderText("");
                    alert.setContentText("There may be existing bugs authored by '" + oldName + "'." +
                        " Do you want to update their authors to '" + newName + "'?");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        changeBugAuthors(oldName, newName);
                    }
                }

                // Close preferences
                Stage stage = (Stage) saveButton.getScene().getWindow();
                stage.hide();
            }
        });
    }

    private void changeBugAuthors(String oldName, String newName) {
        // For each repo, see if there are any bugs under the old author...
        for (String repoName : repositoryNames) {
            List<Bug> theseBugs = bugService.findAll(repoName).stream()
                .filter(b -> b.getAuthor().equals(oldName))
                .collect(Collectors.toList());

            // And change them to the new author
            for (Bug bug : theseBugs) {
                bug.setAuthor(newName);
                bugService.update(repoName, bug);
            }
        }
    }

}
