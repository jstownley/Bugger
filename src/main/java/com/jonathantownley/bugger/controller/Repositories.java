package com.jonathantownley.bugger.controller;

import com.jonathantownley.bugger.Bugger;
import com.jonathantownley.bugger.model.Product;
import com.jonathantownley.bugger.service.ProductService;
import com.jonathantownley.bugger.service.RepositoryService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class Repositories {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ProductService productService;

    @FXML private ChoiceBox repoChoices;
    @FXML private Label dbLabel;
    @FXML private Button loadRepoButton;
    @FXML private Button addRepoButton;
    @FXML private TableView<TableProduct> productTable;
    @FXML private TableColumn<TableProduct, String> nameCol;
    @FXML private TableColumn<TableProduct, String> descCol;

    private ObservableList<String> repoNames = FXCollections.observableArrayList();
    private ObservableList<TableProduct> productsForRepo = FXCollections.observableArrayList();
    private static String dbLabelHeader = "Database file location: ";
    private static String dbLabelExt = ".mv.db";

    @FXML
    private void initialize() {
        repoNames.addAll(repositoryService.getRepositoryNames());
        String firstRepoName = repoNames.get(0);

        repoChoices.setItems(repoNames);
        repoChoices.setValue(firstRepoName);
        repoChoices.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                String repoName = repoNames.get(newValue.intValue());
                reformRepositoryTableData(repoName);
                dbLabel.setText(dbLabelHeader +
                    repositoryService.getRepository(repoName).getDatabaseFileLocation() +
                    dbLabelExt);
            }
        });

        dbLabel.setText(dbLabelHeader +
            repositoryService.getRepository(firstRepoName).getDatabaseFileLocation() +
            dbLabelExt);

        // Set button listeners
        addRepoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Bugger.newRepoStage.showAndWait();
            }
        });

        // Set columns and data for the products table
        formRepositoryTableData(firstRepoName);

        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        descCol.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());

        productTable.getItems().setAll(productsForRepo);
    }

    private void formRepositoryTableData(String repoName) {
        List<Product> products = productService.findAll(repoName);
        for (Product product : products) {
            productsForRepo.add(new TableProduct(product));
        }
    }

    private void reformRepositoryTableData(String repoName) {
        productTable.getItems().clear();
        productsForRepo.clear();
        formRepositoryTableData(repoName);
        productTable.getItems().addAll(productsForRepo);
    }

    // Private class for formatting products into table data
    private class TableProduct {

        private SimpleStringProperty name;
        private SimpleStringProperty description;

        private TableProduct(Product product) {
            this.name = new SimpleStringProperty(product.getName());
            this.description = new SimpleStringProperty(product.getDescription());
        }

        public String getName() {
            return name.get();
        }

        public SimpleStringProperty nameProperty() {
            return name;
        }

        public void setName(String name) {
            this.name.set(name);
        }

        public String getDescription() {
            return description.get();
        }

        public SimpleStringProperty descriptionProperty() {
            return description;
        }

        public void setDescription(String description) {
            this.description.set(description);
        }
    }
}
