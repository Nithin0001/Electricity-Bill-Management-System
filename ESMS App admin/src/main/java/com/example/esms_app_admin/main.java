package com.example.esms_app_admin;

import animatefx.animation.FadeIn;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.util.Objects;


public class main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("admin_login.fxml")));
        stage.setTitle("EBMS");
        Scene scene = new Scene(root, 1280, 720);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
        new FadeIn(root).play();
    }

    public static void main(String[] args) {
        launch();
    }
}

/**
 *
 *
 *
 * Entire Project Has 5966 lines of code including java,fxml,css,sql
 * Copyright @ Nithin R
 *
 *
 *
 */