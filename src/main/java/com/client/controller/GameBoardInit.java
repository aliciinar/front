package com.client.controller;

import com.client.controller.spaceCreate.PropertyInit;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;

public class GameBoardInit extends Application {


    @Override
    public void start(Stage stage) throws IOException {


     //  Parent root = FXMLLoader.load(getClass().getResource("/com.client.controller/GameBoard.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/com.client.controller/gameBoards.fxml"));
        stage.setTitle("Hello World");
        stage.setScene(new Scene(root));

        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
