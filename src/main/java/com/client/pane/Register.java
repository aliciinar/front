package com.client.pane;


import com.client.ClientApplication;
import com.client.controller.StageController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * set up screen for the register pane
 */
public class Register extends GridPane{

    private TextField userTextField;
    private PasswordField pwBox;
    private TextField emailTextField;

    private double width,height;




    public Register(Double width , Double height) {
            this.width = width;
            this.height = height;

            setPrefHeight(height);
            setPrefWidth(width);

            setAlignment(Pos.CENTER);

            setHgap(10);
            setVgap(10);
            setPadding(new Insets(25, 25, 25, 25));

            addRegister();
            addUserName();
            addPassword();
            addEmail();
            addButtons();

    }

    private void addButtons(){
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(addRegisterButton());
        add(hbBtn, 0, 4);
        add(addBackButton() , 1 , 4);
    }

    private Button addRegisterButton(){
        Button button = new Button("Register");
        button.setPrefHeight(50);
        button.setPrefWidth(200);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (userTextField.getText() == "" || pwBox.getText() == "" || emailTextField.getText() == ""){
                    userTextField.setPromptText("Please Give User Name");
                    pwBox.setPromptText("Please Give Password");
                    emailTextField.setPromptText("Please Give Email");
                }

                else{
                    String content = ClientApplication.request.RegisterRequest(userTextField.getText() , pwBox.getText() , emailTextField.getText());
                    if(content.length() != 0){
                        StageController.screenController.activate("Login");
                    }
                    else {
                        userTextField.setText("");
                        userTextField.setPromptText("User or Email Exists");
                    }
                }
            }
        });
        return button;
    }


    private Button addBackButton(){
        Button button = new Button("Back");
        button.setPrefHeight(50);
        button.setPrefWidth(200);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                StageController.screenController.activate("MainMenu");
            }
        });


        return button;
    }


    private void addRegister(){
        Label login = new Label("Register");
        login.setFont(Font.font("Tahoma", FontWeight.NORMAL, 100));
        login.setTextFill(Color.BLUE);
        add(login , 0 ,0);

    }
    private void addUserName(){
        Label userName = new Label("User Name:");
        userName.setFont(Font.font("Tahoma", FontWeight.NORMAL, 50));
        add(userName, 0, 1);

        userTextField = new TextField();
        userTextField.setFont(Font.font(30));
        add(userTextField, 1, 1);

    }
    private void addPassword(){
        Label pw = new Label("Password:  ");
        pw.setFont(Font.font("Tahoma", FontWeight.NORMAL, 50));
        add(pw, 0, 2);

        pwBox = new PasswordField();
        pwBox.setFont(Font.font(30));
        add(pwBox, 1, 2);

    }
    private void addEmail(){
        Label email = new Label("Email:  ");
        email.setFont(Font.font("Tahoma", FontWeight.NORMAL, 50));
        add(email, 0, 3);

        emailTextField = new TextField();
        emailTextField.setFont(Font.font(30));
        add(emailTextField, 1, 3);

    }



}
