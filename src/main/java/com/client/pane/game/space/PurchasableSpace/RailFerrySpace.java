package com.client.pane.game.space.PurchasableSpace;

import com.client.pane.game.player.IPlayer;

public class RailFerrySpace extends SpaceDeed {


    public RailFerrySpace(String name , int cost){
        this.name = name;
        this.rent = cost / 10;
        this.cost = cost;
        state = new NotPurchased();
    }

    @Override
    public void action(IPlayer player) {
       // System.out.println("RailFerry," + name + "player" + player.getName() );

        state = state.Action(player , this);
    }

    public void Purchase(IPlayer player ){
        System.out.println("Normal Player Purchase");
        System.out.println("Alan Adı " + getName() + " Cost " + cost);
        System.out.println("Sahip Olacak Kişi " + player.getName());
        player.moneyTransition(-cost);
        player.purchaseSpecialTile();
        setOwner(player);
        this.rent = player.getNumOfSpecialTile() * rent;
        System.out.println("---------------------Alış veriş bitti------------------");
        System.out.println("SAhip: " + player.getName());
        System.out.println("Sahipin Parası: " + player.getMoney());
        System.out.println("Sahibim : " + owner.getName());
        state = new Purchased();
    }

    @Override
    public String getName() {
        return super.getName();
    }
}

