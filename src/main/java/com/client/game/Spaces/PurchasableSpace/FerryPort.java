package com.client.game.Spaces.PurchasableSpace;

import com.client.game.Player.IPlayer;



public class FerryPort extends SpaceDeed {


    public FerryPort(String name , int cost , int position){
        this.name = name;
        this.rent = cost / 10;
        this.position = position;
        this.cost = cost;
        state = new NotPurchased();
    }

    @Override
    public void action(IPlayer player) {
        state = state.Action(player , this);
    }

    public void Purchase(IPlayer player , int cost){
        player.moneyTransition(cost);
        player.purchaseSpecialTile();
        setOwner(player);
        this.rent = player.getNumOfSpecialTile() * rent;
    }

}
