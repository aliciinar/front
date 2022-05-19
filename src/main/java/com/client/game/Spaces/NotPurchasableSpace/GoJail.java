package com.client.game.Spaces.NotPurchasableSpace;
import com.frontend.Player.IPlayer;
import com.frontend.Spaces.AbstractSpace;


public class GoJail  extends AbstractSpace {

    private final int jailLocation = 0;

    public GoJail(String name ,int pos  ) {
        this.name = name;
        this.position = pos;
    }

    @Override
    public void action(IPlayer player) {
        player.goJail(jailLocation);
    }
}
