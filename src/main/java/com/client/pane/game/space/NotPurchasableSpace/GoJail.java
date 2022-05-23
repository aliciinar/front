package com.client.pane.game.space.NotPurchasableSpace;
import com.client.game.Managers.GameManager;
import com.client.pane.game.Player.IPlayer;
import com.client.pane.game.space.AbstractSpace;
import javafx.application.Platform;


public class GoJail  extends AbstractSpace {

    private final int jailLocation = 0;


    @Override
    public void action(IPlayer player) {
        //System.out.println("GoJail " + "player " + player.getName());
        player.setNextTurn(true); // next player will play game
        player.setPrison(true);
        Platform.runLater(new Runnable(){ // todo thread problem in hear
            @Override
            public void run() {
                GameManager.getInstance().goJail(); // go to jail

            }
// do your GUI stuff here
        });
    }

    @Override
    public String getName() {
        return "Go To Jail";
    }
}
