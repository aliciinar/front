package com.client.pane.game.space.NotPurchasableSpace;
import com.client.pane.game.Player.IPlayer;
import com.client.pane.game.space.AbstractSpace;



public class GoJail  extends AbstractSpace {

    private final int jailLocation = 0;

    public GoJail(String name ,int pos ) {
        this.name = name;
        this.position = pos;
    }

    @Override
    public void action(IPlayer player) {
        player.goJail(jailLocation);
    }
}
