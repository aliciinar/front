package com.client.game.Spaces.PurchasableSpace;

import com.frontend.Player.IPlayer;

public class NotPurchased implements SpaceState{
    @Override
    public SpaceState Action(IPlayer player , SpaceDeed space) {

        boolean purchase = player.purchaseSpace(space.cost);

        //Get Action From Player via GUI

        if(purchase) {
            space.Purchase(player , - space.GetCost());
            return new Purchased();
        }
        else return this;

    }
}
