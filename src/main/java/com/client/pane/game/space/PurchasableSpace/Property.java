package com.client.pane.game.space.PurchasableSpace;

import com.client.pane.game.Player.IPlayer;



public class Property extends SpaceDeed {

    public Property(String name , int cost  ){
        this.name = name;
        this.rent = cost / 10;
        this.cost = cost;
        state = new NotPurchased();
    }

    @Override
    public void action(IPlayer player) {
       // System.out.println("Propery," + name + "player" + player.getName() );

     state = state.Action(player , this);
       // System.out.println("name" + state);
    }

    public void Purchase(IPlayer player ){
        player.moneyTransition(-cost);
        player.purchaseSpecialTile();
        setOwner(player);
        state = new Purchased();

    }


}
