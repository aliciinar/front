package com.client.controller.gameboard;

import com.client.controller.observer.IObserverText;
import com.client.game.Managers.GameManager;
import com.client.pane.game.player.IPlayer;
import com.client.pane.game.player.Player;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Update player info and gui according to the user.
 */
public class UserInformationController extends VBox implements IObserverText {

    Label money;
    Label score;
    IPlayer playerR;

    /**
     * initilize the player field of the gui
     * @param player active player
     */
    public UserInformationController(IPlayer player){
        player.addUserInformation(this);
        playerR = player;
        setPadding(new Insets(20,50,5,15));
        Label userName1= new Label("Username : " + player.getName());
         money = new Label("Money: " + player.getMoney());
         score = new Label("Score: " + player.getScore());
         getChildren().add(userName1);
         getChildren().add(money);
        getChildren().add(score);

    }

    /**
     * update user money and score then show the result at the gui
     * @param player active player
     */
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
                            money.setText("Money: " + playerR.getMoney());
                            score.setText("Score: " + playerR.getScore());

                        }
                    });
                }
                //System.out.println("Move bitti");


            }
        });

        taskThread.start();

    }
}
