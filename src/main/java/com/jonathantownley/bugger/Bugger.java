package com.jonathantownley.bugger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.awt.*;

@SpringBootApplication
public class Bugger extends Application {

    private ConfigurableApplicationContext springContext;
    private Parent root;

    // Public static scene roots for other scenes with Spring dependency injection
    public static Parent prefRoot;


    // Public static stages for other scenes
    public static Stage prefStage;


    // Public static filenames for json data
    public static String preferencesFileName = "./src/main/resources/json/preferences.json";
    public static String repositoriesFileName = "./src/main/resources/json/repositories.json";
    public static String configFileName = "./src/main/resources/json/config.json";

    @Override
    public void init() throws Exception {
        // Set main Bugger app FXML Loader and root
        springContext = SpringApplication.run(Bugger.class);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Home.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean);
        root = fxmlLoader.load();

        // Set Preferences scene FXML Loader and root
        FXMLLoader prefLoader = new FXMLLoader(getClass().getResource("/fxml/Preferences.fxml"));
        prefLoader.setControllerFactory(springContext::getBean);
        prefRoot = prefLoader.load();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Primary scene stage
        primaryStage.setScene(new Scene(root, 810, 500));
        primaryStage.setResizable(false);
        primaryStage.show();

        // Preferences stage
        prefStage = new Stage();
        prefStage.setScene(new Scene(prefRoot, 250, 202));
        prefStage.setResizable(false);
        prefStage.initModality(Modality.APPLICATION_MODAL);
        prefStage.hide();
    }

    public static void main(String[] args) {
        launch(Bugger.class, args);
    }

    @Override
    public void stop() throws Exception {
        springContext.stop();
    }
}
