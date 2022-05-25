package com.client.pane;

import com.client.controller.StageController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.json.JSONArray;

/**
 * set up screen for the scores pane
 */
public class Scores extends VBox {

    public Scores(Double width , Double height , JSONArray scoreWeeks , JSONArray scoreMonth){

        TabPane tabPane = new TabPane();

        tabPane.getTabs().add(setTable(scoreWeeks, "Weekly Scores"));
        tabPane.getTabs().add(setTable(scoreMonth, "Monthly Scores"));

        setPrefHeight(height);
        setPrefWidth(width);

        setSpacing(30);

        getChildren().add(tabPane);
        getChildren().add(addBackButton());
        setAlignment(Pos.TOP_CENTER);



    }

    private Button addBackButton(){
        Button button = new Button("Back");
        button.setPrefHeight(50);
        button.setPrefWidth(200);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                StageController.screenController.removeScreen("Scores");
                StageController.screenController.activate("Session");
            }
        });


        return button;
    }

    private Tab setTable(JSONArray array ,String name){

        Tab tabWeek = new Tab(name);
        tabWeek.setContent(new Table("User Name","Score","Date" , array));
        tabWeek.setClosable(false);
        return tabWeek;

    }




}
