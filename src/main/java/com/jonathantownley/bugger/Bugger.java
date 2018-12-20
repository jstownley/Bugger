package com.jonathantownley.bugger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Bugger extends Application {

    private ConfigurableApplicationContext springContext;
    private Parent root;

    private Stage prefStage;

    // Public static scene roots for other scenes with Spring dependency injection
    public static Parent prefRoot;

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
        primaryStage.setScene(new Scene(root, 810, 500));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(Bugger.class, args);
    }

    @Override
    public void stop() throws Exception {
        springContext.stop();
    }
}
