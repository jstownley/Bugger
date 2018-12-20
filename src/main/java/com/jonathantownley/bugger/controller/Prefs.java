package com.jonathantownley.bugger.controller;

import com.jonathantownley.bugger.Bugger;
import com.jonathantownley.bugger.config.Preferences;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class Prefs {

    @Autowired
    private Preferences preferences;

    @FXML private TextField authorName;
    @FXML private CheckBox showClosed;
    @FXML private CheckBox showRejected;
    @FXML private CheckBox showDuplicates;

    @FXML
    private void initialize() {
        // Initialize the values displayed
        authorName.setText(preferences.getAuthor());
        showClosed.setSelected(preferences.isShowClosed());
        showRejected.setSelected(preferences.isShowRejected());
        showDuplicates.setSelected(preferences.isShowDuplicates());

        // Set event listeners
        authorName.setOnAction(event -> {
            preferences.setAuthor(authorName.getCharacters().toString());
        });

        showClosed.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                preferences.setShowClosed(showClosed.isSelected());
            }
        });

        showRejected.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                preferences.setShowRejected(showRejected.isSelected());
            }
        });

        showDuplicates.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                preferences.setShowDuplicates(showDuplicates.isSelected());
            }
        });
    }

}
