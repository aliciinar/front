package com.client.controller.gameboard;

import com.client.controller.observer.IObserverText;
import com.client.game.Managers.GameManager;
import com.client.pane.game.player.IPlayer;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PlayerTurn extends VBox implements IObserverText {

    Label activeUser;

    public PlayerTurn(IPlayer player){
        GameManager.getInstance().addUserInformation(this);

        setPadding(new Insets(20,50,5,15));
        Label currentUser= new Label("Current User");
        activeUser = new Label(player.getName());
        getChildren().add(currentUser);
        getChildren().add(activeUser);

    }






    @Override
    public void updateOwner(IPlayer player) {
        System.out.println("playyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy");
        System.out.println(player.getName());

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
                            activeUser.setText(player.getName());


                        }
                    });
                }
                //System.out.println("Move bitti");


            }
        });

        taskThread.start();
    }
}
