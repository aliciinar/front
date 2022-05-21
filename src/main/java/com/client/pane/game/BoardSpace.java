package com.client.pane.game;


import com.client.pane.game.Player.IPlayer;
import com.client.pane.game.space.ISpace;
import com.client.pane.game.space.NotPurchasableSpace.StartingPoint;
import javafx.scene.control.Button;

public class BoardSpace extends Button {

    private ISpace space;
    private String owner = "None";
    private Double width , height;
    private int gridX , gridY;

    BoardSpace(Double width , Double height , ISpace space , int gridX , int gridY) {
        this.width = width;
        this.height = height;
        this.gridX = gridX;
        this.gridY = gridY;
        setDisable(true);
        setPrefHeight(height);
        setPrefWidth(width);
        setText(space.getName());
        this.space = space;
    }
    public void action(IPlayer player) {
        this.space.action(player);
        setText(this.space.getName());
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


}
