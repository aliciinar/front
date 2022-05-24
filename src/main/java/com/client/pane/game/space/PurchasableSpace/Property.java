package com.client.pane.game.space.PurchasableSpace;

import com.client.pane.game.player.IPlayer;


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

        System.out.println("Normal Player Purchase");
        System.out.println("Alan Adı " + getName() + " Cost " + cost);
        System.out.println("Sahip Olacak Kişi " + player.getName());

        player.moneyTransition(-cost);
      //  player.purchaseSpecialTile();
        setOwner(player);
         System.out.println("---------------------Alış veriş bitti------------------");
        System.out.println("SAhip: " + player.getName());
        System.out.println("Sahipin Parası: " + player.getMoney());
        System.out.println("Sahibim : " + owner.getName());

        state = new Purchased();

    }


}
