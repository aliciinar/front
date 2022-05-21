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


public class ForgetPassword extends GridPane{

    private TextField emailTextField;
    private Label emailInfo = new Label("");

    private double width,height;

    public ForgetPassword(Double width , Double height) {
            this.width = width;
            this.height = height;

            setPrefHeight(height);
            setPrefWidth(width);

            setAlignment(Pos.CENTER);

            setHgap(10);
            setVgap(10);
            setPadding(new Insets(25, 25, 25, 25));

            emailInfo.setFont(Font.font("Tahoma", FontWeight.NORMAL, 25));
            getChildren().add(emailInfo);
            addEmail();
            addButtons();
    }

    private void addButtons(){
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(addFPButton());
        add(addBackButton(),1,4);
        add(hbBtn, 0, 4);

    }

    private Button addFPButton(){
        Button button = new Button("Reset Password");
        button.setPrefHeight(50);
        button.setPrefWidth(200);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (emailTextField.getText() == ""){
                    emailTextField.setPromptText("Please Give Email");
                }
                else{
                    emailInfo.setText("Please Wait");
                    String content = ClientApplication.request.forgotPasswordRequest(emailTextField.getText());
                    emailInfo.setText(content);

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

    private void addEmail(){
        Label userName = new Label("E-mail:");
        userName.setFont(Font.font("Tahoma", FontWeight.NORMAL, 50));
        add(userName, 0, 1);

        emailTextField = new TextField();
        emailTextField.setFont(Font.font(30));
        add(emailTextField, 1, 1);


    }



}
