package org.example.oop_and_javafx_assignment;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                MainApplication.class.getResource("/org/example/oop_and_javafx_assignment/Main.fxml"));
        Scene scene = new Scene(loader.load(), 820, 540);
        primaryStage.setTitle("FSC CSC325 _ Full Stack Project");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
