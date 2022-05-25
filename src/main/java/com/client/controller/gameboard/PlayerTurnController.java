package com.client.controller.gameboard;

import com.client.controller.observer.IObserverText;
import com.client.game.Managers.GameManager;
import com.client.pane.game.player.IPlayer;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PlayerTurnController extends VBox implements IObserverText {
    // GUI for showing current user.
    // Its an observer.

    Label activeUser;

    public PlayerTurnController(IPlayer player){
        GameManager.getInstance().addUserInformation(this); // set this to gameManager

        setPadding(new Insets(20,50,5,15));
        Label currentUser= new Label("Current User");
        activeUser = new Label(player.getName());
        getChildren().add(currentUser);
        getChildren().add(activeUser);

    }






    @Override
    public void updateOwner(IPlayer player) {

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
                            activeUser.setText(player.getName()); // change the current user value


                        }
                    });
                }


            }
        });

        taskThread.start();
    }
}
