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

@SpringBootApplication
public class Bugger extends Application {

    private ConfigurableApplicationContext springContext;
    private Parent root;

    // Public static scene roots for other scenes with Spring dependency injection
    public static Parent prefRoot;
    public static Parent repoDetailRoot;
    public static Parent newRepoRoot;


    // Public static stages for other scenes
    public static Stage prefStage;
    public static Stage repoDetailStage;
    public static Stage newRepoStage;


    // Public static filenames for json data
    public static String settingsFileName = "./src/main/resources/json/settings.json";
    public static String repositoriesFileName = "./src/main/resources/json/repositories.json";

    @Override
    public void init() throws Exception {
        // Set main Bugger app FXML Loader and root
        springContext = SpringApplication.run(Bugger.class);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Home.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean);
        root = fxmlLoader.load();

        // Set Settings scene FXML Loader and root
        FXMLLoader prefLoader = new FXMLLoader(getClass().getResource("/fxml/Settings.fxml"));
        prefLoader.setControllerFactory(springContext::getBean);
        prefRoot = prefLoader.load();

        // Set Repositories scene FXML Loader and root
        FXMLLoader repoLoader = new FXMLLoader(getClass().getResource("/fxml/Repositories.fxml"));
        repoLoader.setControllerFactory(springContext::getBean);
        repoDetailRoot = repoLoader.load();

        // Set New Repo scene FXML Loader and root
        FXMLLoader newRepoLoader = new FXMLLoader(getClass().getResource("/fxml/NewRepository.fxml"));
        newRepoLoader.setControllerFactory(springContext::getBean);
        newRepoRoot = newRepoLoader.load();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Primary scene stage
        primaryStage.setScene(new Scene(root, 810, 500));
        primaryStage.setResizable(false);
        primaryStage.show();

        // Settings stage
        prefStage = new Stage();
        prefStage.setScene(new Scene(prefRoot, 250, 202));
        prefStage.setResizable(false);
        prefStage.initModality(Modality.APPLICATION_MODAL);
        prefStage.hide();

        // Repositories stage
        repoDetailStage = new Stage();
        repoDetailStage.setScene(new Scene(repoDetailRoot, 500, 250));
        repoDetailStage.setResizable(false);
        repoDetailStage.initModality(Modality.APPLICATION_MODAL);
        repoDetailStage.hide();

        // Add-repo stage
        newRepoStage = new Stage();
        newRepoStage.setScene(new Scene(newRepoRoot, 250, 250));
        newRepoStage.setResizable(false);
        newRepoStage.initModality(Modality.APPLICATION_MODAL);
        newRepoStage.hide();
    }

    public static void main(String[] args) {
        launch(Bugger.class, args);
    }

    @Override
    public void stop() throws Exception {
        springContext.stop();
    }
}
