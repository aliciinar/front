package com.client.pane.game.Player;

import org.springframework.context.annotation.Configuration;


@Configuration

public interface IPlayer {


     int getMoney();

     String getName();

     void goJail(int pos);

     void purchaseSpecialTile();

     int getNumOfSpecialTile();

     void moneyTransition(int amount);

     boolean turn();

     boolean purchaseSpace(int spacePrice);

     void movePlayer(int move);
}
