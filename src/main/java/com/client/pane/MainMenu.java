package com.client.pane;


import com.client.controller.StageController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


/**
 * Set up screen for main menu
 */
public class MainMenu extends VBox {


    private double width , height;

    private static final String IDLE_BUTTON_STYLE = " -fx-background-color: \n" +
            "        #ecebe9,\n" +
            "        rgba(0,0,0,0.05),\n" +
            "        linear-gradient(#dcca8a, #c7a740),\n" +
            "        linear-gradient(#f9f2d6 0%, #f4e5bc 20%, #e6c75d 80%, #e2c045 100%),\n" +
            "        linear-gradient(#f6ebbe, #e6c34d);\n" +
            "    -fx-background-insets: 0,9 9 8 9,9,10,11;\n" +
            "    -fx-background-radius: 50;\n" +
            "    -fx-padding: 15 30 15 30;\n" +
            "    -fx-font-family: \"Helvetica\";\n" +
            "    -fx-font-size: 18px;\n" +
            "    -fx-text-fill: #311c09;\n" +
            " -fx-font-size : 75px; \n" +
            "    -fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.1) , 2, 0.0 , 0 , 1);";

    private static final String HOVERED_BUTTON_STYLE = " -fx-background-color: \n" +
            "        #ecebe9,\n" +
            "        rgba(128,0,0,0.05),\n" +
            "    -fx-background-insets: 0,9 9 8 9,9,10,11;\n" +
            "    -fx-background-radius: 50;\n" +
            "    -fx-padding: 15 30 15 30;\n" +
            "    -fx-font-family: \"Helvetica\";\n" +
            "    -fx-font-size: 18px;\n" +
            "    -fx-text-fill: #311c09;\n" +
            " -fx-font-size : 75px; \n" +
            "    -fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.1) , 2, 0.0 , 0 , 1);";

    public MainMenu(Double width , Double height) {
            this.width = width;
            this.height = height;
            setSpacing(50);
            setAlignment(Pos.CENTER);
            setPrefHeight(this.height);
            setPrefWidth(this.width);
        try {
            Image image = new Image(new FileInputStream("src/main/resources/images/main.jpg"));
            Image image2 = new Image(new FileInputStream("src/main/resources/images/logo.png"));
            ImageView imageView = new ImageView(image2);
            getChildren().add(imageView);
            BackgroundImage bImg = new BackgroundImage(image,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            Background bGround = new Background(bImg);
            setBackground(bGround);

        } catch (FileNotFoundException e) {
            //e.printStackTrace();
        }
        getChildren().add(loginButton());
        getChildren().add(registerButton());


    }

    private Button commonButton(String name) {

        Button button = new Button(name);
        button.setOnMouseEntered(e -> button.setStyle(HOVERED_BUTTON_STYLE));
        button.setOnMouseExited(e -> button.setStyle(IDLE_BUTTON_STYLE));
        button.setStyle("-fx-font-size: 5em; ");
        button.setPrefWidth(this.width/2);
        button.setPrefHeight(this.height / 6 );
        button.setAlignment(Pos.CENTER);
        return button;
    }


    private Button loginButton() {
        Button button = commonButton("Login");
        button.setStyle(IDLE_BUTTON_STYLE);
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
        button.setStyle(IDLE_BUTTON_STYLE);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                StageController.screenController.activate("Register");
            }
        });
        return button;
    }


}
