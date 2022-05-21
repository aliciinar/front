package com.client.pane.game;

import com.client.pane.game.space.NotPurchasableSpace.JailVisit;
import com.client.pane.game.space.NotPurchasableSpace.StartingPoint;
import com.client.pane.game.space.PurchasableSpace.Property;
import com.client.pane.game.space.PurchasableSpace.RailFerrySpace;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class Board extends GridPane {

    private Double width , height;
    private Set<BoardSpace> spaces = new HashSet<>();

    public Board (Double width , Double height ) {
        this.width = width;
        this.height = height;

        setPrefHeight(height);
        setPrefWidth(width);

        setAlignment(Pos.TOP_LEFT);
        BackgroundFill background_fill = new BackgroundFill(Color.WHITE,
                CornerRadii.EMPTY, Insets.EMPTY);

        // create Background
        Background background = new Background(background_fill);
        setBackground(background);

        spaces.add(new BoardSpace(width / 5 , height /5 , new StartingPoint() , 0, 4) );
        spaces.add(new BoardSpace(width / 5 , height /5 , new Property("Ankara" , 500) , 0, 3) );
        spaces.add(new BoardSpace(width / 5 , height /5 , new Property("Diyarbakır" , 1000) , 0, 2) );
        spaces.add(new BoardSpace(width / 5 , height /5 , new RailFerrySpace("Liman 1" , 500) , 0, 1) );
        spaces.add(new BoardSpace(width / 5 , height /5 , new JailVisit() , 4 , 0));


        for(BoardSpace space : spaces){
            add(space , space.getGridX() , space.getGridY());
        }







    }

}
