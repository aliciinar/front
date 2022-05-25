package com.client.pane.game.player;

import com.client.controller.observer.IObservable;
import com.client.pane.game.space.ISpace;
import com.client.pane.game.space.purchasableSpace.RailFerrySpace;
import org.springframework.context.annotation.Configuration;


@Configuration

public interface IPlayer extends IObservable {

     // interface of the player.this interface is extending IObservable in order to notify GUI when changes occur

     int getMoney(); // get money of the player

     String getName(); // get name of the player

     int getPosition(); // get position of the player

     void purchaseSpecialTile(RailFerrySpace railFerrySpace); // purchase special tile which are rail way and ferry port


     void moneyTransition(int amount); // player money is increase or decrease


     boolean purchaseSpace(int spacePrice); // purchase an deed

     void movePlayer(int move); // set player new position according to the space index

     UserType getUserType(); // get user type of the player

     void  action(ISpace space, int diceVal1, int diceVal2); // player is determining action according to the dice values.
                                                            // player can be in prison , can go jail or can be in not prison and these are determined according to the states
                                                            // this actions are determined with these method
     // player determine its state according the roll value. After determining its state it give what will happen responsibility to the space

     boolean isNextTurn(); // check whether player  will play again or not

     void  setNextTurn(boolean nextTurn); // set player will play again or not

     boolean isPrison(); // check whether the player in prison or not

     void  setPrison(boolean isPrison); // set the player in prison or not

     void  checkFalseMoneyIncrease(boolean jailCheck);  // we are setting the player to jail after the GUI move.
     // so if the player roll double three times and pass from start point it can create an false increase in money
     // it is not suitable for oop but we can not find a suitable solution  because of time constraints

     void  setScore(int price); // set score of the player

     int getScore(); // get score of the player


}
