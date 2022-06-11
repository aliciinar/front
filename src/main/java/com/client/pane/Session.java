package com.client.pane;

import com.client.ClientApplication;
import com.client.controller.StageController;
import com.client.controller.gameboard.sceneTypes.BotScene;
import com.client.dto.jsonObj.GameObj;
import com.client.game.Managers.GameManager;
import com.client.pane.game.player.BotAI;
import com.client.pane.game.player.IPlayer;
import com.client.pane.game.player.Player;
import com.client.controller.gameboard.sceneTypes.PlayerScene;
import com.google.gson.JsonParser;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;
import java.util.Random;

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

        ResponseEntity<String> response = ClientApplication.multiplayerRequest.playMultiplayer("sait" , token);
        ResponseEntity<String> response2 = ClientApplication.multiplayerRequest.addAction("sait","b",1,2,token);
        ClientApplication.multiplayerRequest.addAction("sait","c",3,4,token);
        ClientApplication.multiplayerRequest.deleteAction("sait",token);


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
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {


                Thread taskThread = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        while(true){
                            ResponseEntity<String> response = ClientApplication.multiplayerRequest.playMultiplayer(name , token);
                            if(response.getStatusCode() == HttpStatus.FOUND) {
                                ObjectMapper mapper = new ObjectMapper();
                                GameObj gameObj = null;
                                try {
                                    gameObj =  mapper.readValue(response.getBody() , GameObj.class);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                System.out.println(gameObj.getUser1());
                                break;
                            }
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {

                            }

                        }

                        IPlayer player1 = new Player(name);
                        IPlayer player2 = new BotAI();
                        GameManager.getInstance().addPlayer(player1, new PlayerScene());
                        GameManager.getInstance().addPlayer(player2,new BotScene());
                        Pane root = null;
                        try {
                            root = FXMLLoader.load(getClass().getResource("/com.client.controller/gameBoards.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        StageController.screenController.addScreen("Game" , root);
                        StageController.screenController.activate("Game");

                    }
                });
                taskThread.start();



            }
        });
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

