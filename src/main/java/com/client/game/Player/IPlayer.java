package com.client.game.Player;

import org.springframework.context.annotation.Configuration;


@Configuration

public interface IPlayer {


     int getMoney();

     void goJail(int pos);

     void purchaseSpecialTile();

     int getNumOfSpecialTile();

     void moneyTransition(int amount);

     boolean turn();

     boolean purchaseSpace(int spacePrice);

     void movePlayer(int move);
}
