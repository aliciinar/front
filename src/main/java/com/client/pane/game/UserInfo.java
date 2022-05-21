package com.client.pane.game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;


public class UserInfo extends HBox {

    private Double width , height;

    public UserInfo(Double width , Double height ) {
        this.width = width;
        this.height = height;

        setPrefHeight(height);
        setPrefWidth(width);


        BackgroundFill background_fill = new BackgroundFill(Color.BLACK,
                CornerRadii.EMPTY, Insets.EMPTY);

        // create Background
        Background background = new Background(background_fill);
        setBackground(background);

    }

}
