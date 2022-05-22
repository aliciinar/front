package com.client.pane.game;

import com.client.game.Managers.GameManager;
import com.client.game.Managers.SpaceManager;
import com.client.pane.game.Player.BotAI;
import com.client.pane.game.Player.IPlayer;
import com.client.pane.game.Player.Player;
import com.client.pane.game.space.SpaceCreation.ISpaceCreatorFactory;
import com.client.pane.game.space.SpaceCreation.NormalCreation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartGame  extends Application {


    @Override
    public void start(Stage stage) throws IOException {

        IPlayer player1 = new Player("Sait");
        IPlayer player2 = new BotAI();
        GameManager.getInstance().addPlayer(player1, new PlayerScene());
        GameManager.getInstance().addPlayer(player2,new BotScene());

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
