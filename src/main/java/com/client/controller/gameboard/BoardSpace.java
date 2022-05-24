package com.client.controller.gameboard;


import com.client.pane.game.player.IPlayer;
import com.client.pane.game.space.ISpace;

import javafx.geometry.Pos;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;



public class BoardSpace extends VBox {

    private ISpace space;
    private String owner = "None";
    private Double width , height;
    private Button button;
    private HBox hBox;
    private int gridX , gridY;


    BoardSpace(Double width , Double height , ISpace space , int gridX , int gridY) {
        this.width = width;
        this.height = height;
        this.gridX = gridX;
        this.gridY = gridY;
        setDisable(true);
        button = new Button(space.getName());
        button.setPrefWidth(width);
        button.setPrefHeight(height * 3 / 4);
        this.space = space;
        hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPrefHeight(height/2);
        hBox.setPrefWidth(width);
        hBox.setStyle("-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");
        getChildren().add(hBox);
        getChildren().add(button);
    }
    public void action(IPlayer player) {
        this.space.action(player);
        button.setText(this.space.getName());

    }
    public void update() {
        button.setText("asd");
    }
    public void setOwner(String owner) {
        this.owner = owner ;
    }
    public int getGridX() {
        return gridX;
    }
    public int getGridY() {
        return gridY;
    }

    public void putImage(ImageView image){
        hBox.getChildren().add(image);
    }
    public void removeImage(ImageView image) {
        hBox.getChildren().remove(image);
    }
    public  ISpace  getSpace(){ return  space; }


}
