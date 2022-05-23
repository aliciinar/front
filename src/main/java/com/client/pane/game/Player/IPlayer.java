package com.client.pane.game.Player;

import com.client.pane.game.space.ISpace;
import org.springframework.context.annotation.Configuration;


@Configuration

public interface IPlayer {


     int getMoney();

     String getName();

     void goJail(int pos);

     int getPosition();

     void purchaseSpecialTile();

     int getNumOfSpecialTile();

     void moneyTransition(int amount);

     boolean turn(int diceValue1 , int diceValue2);

     boolean purchaseSpace(int spacePrice);

     void movePlayer(int move);

     String getUserType();

     void  action(ISpace space, int diceVal1, int diceVal2);

     boolean isNextTurn();

     void  setNextTurn(boolean nextTurn);

     boolean isPrison();

     void  setPrison(boolean isPrison);


}
