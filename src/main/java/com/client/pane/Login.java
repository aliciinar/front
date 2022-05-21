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
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Login extends GridPane{

    private TextField userTextField;
    private PasswordField pwBox;

    private double width,height;

    public Login(Double width , Double height) {
            this.width = width;
            this.height = height;

            setPrefHeight(height);
            setPrefWidth(width);

            setAlignment(Pos.CENTER);

            setHgap(10);
            setVgap(10);
            setPadding(new Insets(25, 25, 25, 25));

            addLogin();
            addUserName();
            addPassword();
            addButtons();
    }

    private void addButtons(){
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(addLoginButton());
        hbBtn.getChildren().add(addForgetPasswordButton());
        add(hbBtn, 0, 4);
        add(addBackButton(),1,4);
    }

    private Button addLoginButton(){
        Button button = new Button("Login");
        button.setPrefHeight(50);
        button.setPrefWidth(200);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (userTextField.getText() == "" || pwBox.getText() == ""){
                    userTextField.setPromptText("Please Give User Name");
                    pwBox.setPromptText("Please Give Password");
                }
                else{
                    String content = ClientApplication.request.loginRequest(userTextField.getText() , pwBox.getText());
                    if(content.length() != 0){

                        Pane pane = new Session(width , height , userTextField.getText() , content);
                        StageController.screenController.addScreen("Session" , pane);
                        StageController.screenController.activate("Session");
                    }
                    else {
                        pwBox.setText("");
                        pwBox.setPromptText("Wrong Credentials");
                    }

                }
            }
        });
        return button;
    }

    private Button addForgetPasswordButton(){
        Button button = new Button("Forgot Password?");
        button.setPrefHeight(50);
        button.setPrefWidth(200);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                StageController.screenController.activate("ForgetPassword");
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

    private void addLogin(){
        Label login = new Label("Login");
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


}
