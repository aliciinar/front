package com.client.pane.game.player;

import com.client.pane.game.space.ISpace;
import org.springframework.context.annotation.Configuration;


@Configuration

public interface IPlayer {


     int getMoney();

     String getName();

     int getPosition();

     void purchaseSpecialTile();

     int getNumOfSpecialTile();

     void moneyTransition(int amount);


     boolean purchaseSpace(int spacePrice);

     void movePlayer(int move);

     UserType getUserType();

     void  action(ISpace space, int diceVal1, int diceVal2);

     boolean isNextTurn();

     void  setNextTurn(boolean nextTurn);

     boolean isPrison();

     void  setPrison(boolean isPrison);

     void  checkFalseMoneyIncrease(boolean jailCheck);




}
