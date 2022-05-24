package com.client.controller.gameboard;

import com.client.game.Managers.GameManager;
import com.client.pane.game.space.PurchasableSpace.SpaceDeed;

public class BotScene implements IPrepareScene{


    @Override
    public void notPurchased(SpaceDeed space,GameBoardController gameBoardController) {

        System.out.println("Bot satın alma");
    }



    @Override
    public void nextTurnNormal(GameBoardController gameBoardController) {
        gameBoardController.activateButtons(true , true , true,true);
        gameBoardController.roll();
        System.out.println("nextTurnNormal");

    }

    @Override
    public void nextTurnJail(GameBoardController gameBoardController) {
        System.out.println("botnextTurnJail");
        gameBoardController.activateButtons(true,true,true,false);
    }




    @Override
    public void endTurn() {
        GameManager.getInstance().getGameBoard().activateButtons(true,true,false,true);
    }
}
