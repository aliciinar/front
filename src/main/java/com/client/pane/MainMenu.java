package com.client.pane;


import com.client.controller.StageController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;


public class MainMenu extends VBox {


    private double width , height;

    public MainMenu(Double width , Double height) {
            this.width = width;
            this.height = height;
            setSpacing(50);
            getChildren().add(loginButton());
            getChildren().add(registerButton());
            setAlignment(Pos.CENTER);
            setPrefHeight(this.height);
            setPrefWidth(this.width);


    }

    private Button commonButton(String name) {
        Button button = new Button(name );
        button.setStyle("-fx-font-size: 5em; ");
        button.setPrefWidth(this.width/2);
        button.setPrefHeight(this.height / 6 );
        button.setAlignment(Pos.CENTER);
        return button;
    }


    private Button loginButton() {
        Button button = commonButton("Login");

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                StageController.screenController.activate("Login");
            }
        });

        return button;
    }

    private Button registerButton() {

        Button button = commonButton("Register");

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                StageController.screenController.activate("Register");
            }
        });
        return button;
    }


}
