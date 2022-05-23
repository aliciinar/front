package com.client.pane.game;

import com.client.game.Managers.GameManager;
import com.client.pane.game.space.PurchasableSpace.SpaceDeed;

public class PlayerScene implements  IPrepareScene{


    @Override
    public void notPurchased(SpaceDeed space) {
        System.out.println("Player Scene satın alma ekranı");
        GameManager.getInstance().getGameBoard().activateButtons(true,false,false);
        GameManager.getInstance().setDeed(space);


    }

    @Override
    public void normal() {
        System.out.println("Player Scene normal turn");
        GameManager.getInstance().getGameBoard().activateButtons(true,true,false);
    }




    @Override
    public void playerEnd(GameBoard gameBoard) {

        gameBoard.activateButtons(true , true , true);
    }

    @Override
    public  void  nextTurnNormal(GameBoard gameBoard){
        gameBoard.activateButtons(false , true , true);
        System.out.println("Player scene user next turn normal");

    }

    @Override
    public void nextTurnJail(GameBoard gameBoard) {
        System.out.println("user next turn jail");
    }

    @Override
    public void endTurn() {
        GameManager.getInstance().getGameBoard().activateButtons(true,true,false);
    }
}
