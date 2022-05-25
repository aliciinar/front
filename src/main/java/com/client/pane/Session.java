package com.client.pane;

import com.client.ClientApplication;
import com.client.controller.StageController;
import com.client.controller.gameboard.sceneTypes.BotScene;
import com.client.game.Managers.GameManager;
import com.client.pane.game.player.BotAI;
import com.client.pane.game.player.IPlayer;
import com.client.pane.game.player.Player;
import com.client.controller.gameboard.sceneTypes.PlayerScene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import org.json.JSONArray;

import java.io.IOException;

/**
 * set up screen for the session page
 */
public class Session extends VBox {


    private double width , height;
    private String name ;
    private int id;
    public static String token;

    public Session(Double width , Double height , String name , String token ) {
        this.width = width;
        this.height = height;
        this.name = name;
        this.token = token;


        setSpacing(50);

        Label welcome = new Label("Welcome " + this.name);
        welcome.setFont(Font.font(50));
        getChildren().add(welcome);

        getChildren().add(playSingleButton());
        getChildren().add(playMultiplayerButton());
        getChildren().add(leaderBordButton());
        getChildren().add(addLogoutButton());

        setAlignment(Pos.CENTER);
        setPrefHeight(this.height);
        setPrefWidth(this.width);


    }

    private Button commonButton(String name) {
        Button button = new Button(name );
        button.setStyle("-fx-font-size: 3em; ");
        button.setPrefWidth(this.width/2);
        button.setPrefHeight(this.height / 6 );
        button.setAlignment(Pos.CENTER);
        return button;
    }

    private Button addLogoutButton(){
        Button button = new Button("Logout");
        button.setPrefHeight(50);
        button.setPrefWidth(200);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                StageController.screenController.removeScreen("Session");
                StageController.screenController.activate("MainMenu");
            }
        });


        return button;
    }


    private Button playSingleButton() {
        Button button = commonButton("Play Single");

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {


                try {

                    IPlayer player1 = new Player(name);
                    IPlayer player2 = new BotAI();
                    GameManager.getInstance().addPlayer(player1, new PlayerScene());
                    GameManager.getInstance().addPlayer(player2,new BotScene());
                    Pane root = FXMLLoader.load(getClass().getResource("/com.client.controller/gameBoards.fxml"));
                    StageController.screenController.addScreen("Game" , root);
                    StageController.screenController.activate("Game");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        return button;
    }

    private Button playMultiplayerButton() {

        Button button = commonButton("Play Multiplayer");
        return button;
    }

    private Button leaderBordButton() {

        Button button = commonButton("LeaderBoard");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                JSONArray scoreWeeks = ClientApplication.request.GetWeekTableRequest(token);
                JSONArray scoreMonths = ClientApplication.request.GetMonthTableRequest(token);
                System.out.println("Here" + scoreMonths.length());
                StageController.screenController.addScreen("Scores",new Scores(width,height,scoreWeeks,scoreMonths ));
                StageController.screenController.activate("Scores");
            }
        });
        return button;
    }



}
