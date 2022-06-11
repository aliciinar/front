package com.client.pane.game.space.notPurchasableSpace;
import com.client.game.Managers.GameManager;
import com.client.pane.game.player.IPlayer;
import com.client.pane.game.space.AbstractSpace;
import javafx.application.Platform;

/**
 * Go to jail class
 * this is the space where player go to the jail
 */
public class GoJail  extends AbstractSpace {

    private final int jailLocation = 0;


    @Override
    public void action(IPlayer player) {
        player.setNextTurn(true); // next player will play game
        player.setPrison(true);

        Thread taskThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 1 ; i++) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {

                    }

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            GameManager.getInstance().goJail(); // go to jail

                        }
                    });
                }


                //  GameManager.getInstance().nextTurn(); // go to jail




            }

        });





        taskThread.start();

    }


    @Override
    public String getName() {
        return "Go To Jail";
    }
}
