package com.client.pane;

import com.client.ClientApplication;
import com.client.controller.StageController;
import com.client.controller.gameboard.sceneTypes.BotScene;
import com.client.controller.gameboard.sceneTypes.MultiPlayerScene;
import com.client.dto.jsonObj.GameObj;
import com.client.game.Managers.GameManager;
import com.client.game.Managers.GameType;
import com.client.pane.game.MatchScreen;
import com.client.pane.game.player.BotAI;
import com.client.pane.game.player.IPlayer;
import com.client.pane.game.player.MultiPlayer;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

/**
 * set up screen for the session page
 */
public class Session extends VBox {


    private double width , height;
    public static String name ;
    private int id;
    public static String token;

    public Session(Double width , Double height , String name , String token ) {
        this.width = width;
        this.height = height;
        this.name = name;
        this.token = token;


        setSpacing(50);

        Label welcome = new Label("Welcome " + this.name);
        welcome.setTextFill(Color.WHITE);
        welcome.setFont(Font.font(75));
        getChildren().add(welcome);

        getChildren().add(playSingleButton());
        getChildren().add(playMultiplayerButton());
        getChildren().add(leaderBordButton());
        getChildren().add(addLogoutButton());

        setAlignment(Pos.CENTER);
        setPrefHeight(this.height);
        setPrefWidth(this.width);

        try {
            Image image = new Image(new FileInputStream("src/main/resources/images/mainscreen.png"));
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


    }

    private Button commonButton(String name) {
        Button button = new Button(name );
        button.setOnMouseEntered(e -> button.setStyle(MainMenu.HOVERED_BUTTON_STYLE));
        button.setOnMouseExited(e -> button.setStyle(MainMenu.IDLE_BUTTON_STYLE));
        button.setStyle("-fx-font-size: 3em; ");
        button.setPrefWidth(this.width/2);
        button.setPrefHeight(this.height / 6 );
        button.setAlignment(Pos.CENTER);
        return button;
    }

    private Button addLogoutButton(){
        Button button = new Button("Logout");
        button.setOnMouseEntered(e -> button.setStyle(MainMenu.HOVERED_BUTTON_STYLE));
        button.setOnMouseExited(e -> button.setStyle(MainMenu.IDLE_BUTTON_STYLE));
        button.setStyle(MainMenu.IDLE_BUTTON_STYLE);
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
        button.setStyle(MainMenu.IDLE_BUTTON_STYLE);
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
        button.setStyle(MainMenu.IDLE_BUTTON_STYLE);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                StageController.screenController.addScreen("MatchScreen", new MatchScreen());
                StageController.screenController.activate("MatchScreen");


                Thread taskThread = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        ObjectMapper mapper = new ObjectMapper();
                        GameObj gameObj = null;
                        while(true){

                            ResponseEntity<String> response = ClientApplication.multiplayerRequest.playMultiplayer(name , token);
                            if(response.getStatusCode() == HttpStatus.FOUND) {

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
                        IPlayer player1;
                        IPlayer player2;
                        if(gameObj.getUser1().equals(name)){
                            player1 = new Player(name);
                            player2 = new MultiPlayer(gameObj.getUser2());
                            GameManager.getInstance().addPlayer(player1, new PlayerScene());
                            GameManager.getInstance().addPlayer(player2,new MultiPlayerScene());
                        }
                        else{
                            player2 = new Player(name);
                            player1 = new MultiPlayer(gameObj.getUser1());
                            GameManager.getInstance().addPlayer(player1,new MultiPlayerScene());
                            GameManager.getInstance().addPlayer(player2, new PlayerScene());

                        }



                        GameManager.getInstance().SetGameType(GameType.Multiplayer); // set game type of the game
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
        button.setStyle(MainMenu.IDLE_BUTTON_STYLE);
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

