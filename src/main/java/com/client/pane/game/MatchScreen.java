package com.client.pane.game;

import com.client.game.Managers.GameManager;
import com.client.pane.game.player.IPlayer;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MatchScreen  extends BorderPane {


    public  MatchScreen(){

        Label setScene= new Label("Waiting Other Player!!!");
        setScene.setFont(new Font("Arial",75));
        setCenter(setScene);


    }

    private  void  waitPlayer(){
        boolean match = true;
        while (match){

        }


    }



}
