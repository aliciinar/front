package com.client.controller.gameboard;

import com.client.game.Managers.GameManager;
import com.client.pane.game.space.PurchasableSpace.SpaceDeed;

public class PlayerScene implements  IPrepareScene{


    @Override
    public void notPurchased(SpaceDeed space,GameBoardController gameBoardController) {
        System.out.println("Player Scene satın alma ekranı");
        gameBoardController.activateButtons(true,false,false,true);
        GameManager.getInstance().setDeed(space);


    }

    @Override
    public  void  nextTurnNormal(GameBoardController gameBoardController){
        gameBoardController.activateButtons(false , true , true,true);
        System.out.println("Player scene user next turn normal");

    }

    @Override
    public void nextTurnJail(GameBoardController gameBoardController) {
        System.out.println("user next turn jail");
        gameBoardController.activateButtons(true,true,true,false);
    }

    @Override
    public void endTurn() {
        GameManager.getInstance().getGameBoard().activateButtons(true,true,false,true);
    }


}
