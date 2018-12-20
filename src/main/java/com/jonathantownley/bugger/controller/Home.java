package com.jonathantownley.bugger.controller;

import com.jonathantownley.bugger.Bugger;
import com.jonathantownley.bugger.config.Preferences;
import com.jonathantownley.bugger.model.Bug;
import com.jonathantownley.bugger.service.BugService;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import java.io.IOException;
import java.net.URL;
import java.util.*;

@Controller
public class Home {

    @Autowired
    private BugService bugService;

    @Autowired
    private List<String> repositoryNames;

    @FXML private MenuButton optsButton;
    @FXML private MenuItem prefOpts;

    @FXML private TableView<TableBug> bugTable;
    @FXML private TableColumn<TableBug, String> repoCol;
    @FXML private TableColumn<TableBug, Long> bugNumCol;
    @FXML private TableColumn<TableBug, Date> dateCol;
    @FXML private TableColumn<TableBug, String> authorCol;
    @FXML private TableColumn<TableBug, String> titleCol;
    @FXML private TableColumn<TableBug, String> productCol;
    @FXML private TableColumn<TableBug, String> severityCol;
    @FXML private TableColumn<TableBug, String> statusCol;

    private Set<String> bugStrings;

    private ObservableList<TableBug> allBugs = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Get all the bugs from all repos
        for (String repoName : repositoryNames) {
            List<Bug> theseBugs = bugService.findAll(repoName);
            for (Bug bug : theseBugs) {
                TableBug tb = new TableBug(bug);
                allBugs.add(tb);
            }
        }

        // Add bugs to the TableView
        repoCol.setCellValueFactory(cellData -> cellData.getValue().repoNameProperty());
        bugNumCol.setCellValueFactory(cellData -> cellData.getValue().bugNumProperty().asObject());
        dateCol.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        authorCol.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
        titleCol.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        productCol.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        severityCol.setCellValueFactory(cellData -> cellData.getValue().severityProperty());
        statusCol.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        bugTable.getItems().setAll(allBugs);

        // Set the menu button options' actions
        prefOpts.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                showPreferences();
            }
        });


    }

    private void showPreferences() {
//        try {
//            Parent prefsRoot = null;
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Preferences.fxml"));
////            fxmlLoader.setController(Prefs.class);
//            prefsRoot = fxmlLoader.load();
            Stage prefStage = new Stage();
            prefStage.setScene(new Scene(Bugger.prefRoot, 250, 202));
            prefStage.setResizable(false);
            prefStage.show();
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
    }



    // Private class for formatting bugs into table data
    private class TableBug {

        private SimpleStringProperty repoName;
        private LongProperty bugNum;
        private ObjectProperty<Date> date;
        private SimpleStringProperty author;
        private SimpleStringProperty title;
        private SimpleStringProperty productName;
        private SimpleStringProperty severity;
        private SimpleStringProperty status;

        private TableBug(Bug bug) {
            this.repoName = new SimpleStringProperty(bug.getRepositoryName());
            this.bugNum = new SimpleLongProperty(bug.getId());
            this.date = new SimpleObjectProperty<Date>(bug.getDate());
            this.author = new SimpleStringProperty(bug.getAuthor());
            this.title = new SimpleStringProperty(bug.getTitle());
            this.productName = new SimpleStringProperty(bug.getProduct().getName());
            this.severity = new SimpleStringProperty(bug.getSeverity());
            this.status = new SimpleStringProperty(bug.getStatus());
        }

        public String getRepoName() {
            return repoName.get();
        }

        public SimpleStringProperty repoNameProperty() {
            return repoName;
        }

        public void setRepoName(String repoName) {
            this.repoName.set(repoName);
        }

        public Long getBugNum() {
            return bugNum.get();
        }

        public LongProperty bugNumProperty() {
            return bugNum;
        }

        public void setBugNum(Long bugNum) {
            this.bugNum.set(bugNum);
        }

        public Date getDate() {
            return date.get();
        }

        public ObjectProperty<Date> dateProperty() {
            return date;
        }

        public void setDate(Date date) {
            this.date.set(date);
        }

        public String getAuthor() {
            return author.get();
        }

        public SimpleStringProperty authorProperty() {
            return author;
        }

        public void setAuthor(String author) {
            this.author.set(author);
        }

        public String getTitle() {
            return title.get();
        }

        public SimpleStringProperty titleProperty() {
            return title;
        }

        public void setTitle(String title) {
            this.title.set(title);
        }

        public String getProductName() {
            return productName.get();
        }

        public SimpleStringProperty productNameProperty() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName.set(productName);
        }

        public String getSeverity() {
            return severity.get();
        }

        public SimpleStringProperty severityProperty() {
            return severity;
        }

        public void setSeverity(String severity) {
            this.severity.set(severity);
        }

        public String getStatus() {
            return status.get();
        }

        public SimpleStringProperty statusProperty() {
            return status;
        }

        public void setStatus(String status) {
            this.status.set(status);
        }
    }
}
