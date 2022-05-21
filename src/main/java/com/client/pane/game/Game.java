package com.client.pane.game;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class Game extends VBox {

    private double width , height ;

    public Game(Double width , Double height) {
        this.width = width;
        this.height = height;

        setPrefHeight(height);
        setPrefWidth(width);

        getChildren().add(new UserInfo(width , height/8));
        getChildren().add(new Board(width , 5 * height/8));
        getChildren().add(new UserInfo(width ,  2 * height/8));
    }

}
