package com.client.pane.game.space.NotPurchasableSpace;
import com.client.pane.game.Player.IPlayer;
import com.client.pane.game.space.AbstractSpace;



public class GoJail  extends AbstractSpace {

    private final int jailLocation = 0;


    @Override
    public void action(IPlayer player) {
        player.goJail(jailLocation);
    }

    @Override
    public String getName() {
        return "Go To Jail";
    }
}
