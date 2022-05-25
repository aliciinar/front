package com.client.controller.gameboard;


import com.client.controller.observer.IObserverText;
import com.client.game.Managers.GameManager;
import com.client.pane.game.player.IPlayer;
import com.client.pane.game.player.Player;
import com.client.pane.game.space.ISpace;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.HashMap;


public class BoardSpaceController extends VBox  implements IObserverText {

    //  this class is implementing spaces in GUI.
    // change values according to the price changes in GUI.

    private ISpace space;
    private String owner = "None";
    private Double width , height;
    private Button button;
    private HBox hBox;
    private int gridX , gridY;

    private HashMap<IPlayer,Color> color = new HashMap<>();

    BoardSpaceController(Double width , Double height , ISpace space , int gridX , int gridY) {
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
        colorSet();
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


    @Override
    public void updateOwner(IPlayer player) { // space is purchased. Set owner information values to the GUI.
        Thread taskThread = new Thread(new Runnable() {
            @Override
            public void run() {

                for(int i = 0; i <1; i++){

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {

                    }
                    final int d = i;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            button.setText(space.getName()); // set owner of the space
                            button.setBackground(new Background(new BackgroundFill(color.get(player), new CornerRadii(0), Insets.EMPTY)));

                        }
                    });
                }


            }
        });

        taskThread.start();


    }

    private  void colorSet(){
        color.put(GameManager.getInstance().getPlayerIndex(0),Color.AQUA);
        color.put(GameManager.getInstance().getPlayerIndex(1),Color.CORAL);
    }





}
