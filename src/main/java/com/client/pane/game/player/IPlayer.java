package com.client.pane.game.player;

import com.client.controller.observer.IObservable;
import com.client.pane.game.space.ISpace;
import com.client.pane.game.space.purchasableSpace.RailFerrySpace;
import org.springframework.context.annotation.Configuration;


@Configuration


/**
 * interface of the player.this interface is extending IObservable in order to notify GUI when changes occur
 * a player can be bot or normal player
 */
public interface IPlayer extends IObservable {


     /**
      * get money of the player
      * @return money
      */
     int getMoney(); // get money of the player

     /**
      * get name of the player
      * @return name
      */
     String getName();

     /**
      * // get position of the player
      * @return pos
      */

     int getPosition();

     /**
      * // purchase special tile which are rail way and ferry port
      * @param railFerrySpace
      */

     void purchaseSpecialTile(RailFerrySpace railFerrySpace);

     /**
      * // player money is increase or decrease
      * @param amount
      */
     void moneyTransition(int amount);

     /**
      * // purchase an deed
      * @param spacePrice
      * @return
      */
     boolean purchaseSpace(int spacePrice);

     /**
      * // set player new position according to the space index
      * @param move
      */
     void movePlayer(int move);

     /**
      *  // get user type of the player
      * @return
      */
     UserType getUserType();

     /**
      * player is determining action according to the dice values.
      * player can be in prison , can go jail or can be in not prison and these are determined according to the states
      * this actions are determined with these method
      *  player determine its state according the roll value. After determining its state it give what will happen responsibility to the space
      * @param space
      * @param diceVal1
      * @param diceVal2
      */
     void  action(ISpace space, int diceVal1, int diceVal2);

     /**
      * check whether player  will play again or not
      * @return
      */
     boolean isNextTurn();

     /**
      * set player will play again or not
      * @param nextTurn
      */
     void  setNextTurn(boolean nextTurn); //

     /**
      * // check whether the player in prison or not
      * @return
      */
     boolean isPrison();

     /**
      * // set the player in prison or not
      * @param isPrison
      */
     void  setPrison(boolean isPrison);

     /**
      * // we are setting the player to jail after the GUI move.
      *   so if the player roll double three times and pass from start point it can create an false increase in money
      *      // it is not suitable for oop but we can not find a suitable solution  because of time constraints
      * @param jailCheck
      */
     void  checkFalseMoneyIncrease(boolean jailCheck);

     /**
      * set score of the player
      * @param price
      */
     void  setScore(int price);

     /**
      * get score of the player
      * @return score
      */
     int getScore();

}
