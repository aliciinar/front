package com.client.controller.gameboard.sceneTypes;

import com.client.controller.gameboard.GameBoardController;
import com.client.game.Managers.GameManager;
import com.client.pane.game.space.purchasableSpace.SpaceDeed;

public class BotScene implements IPrepareScene{


    @Override
    public void notPurchased(SpaceDeed space, GameBoardController gameBoardController) {

    }



    @Override
    public void nextTurnNormal(GameBoardController gameBoardController) {
        gameBoardController.activateButtons(true , true , true,true);
        gameBoardController.roll();


    }

    @Override
    public void nextTurnJail(GameBoardController gameBoardController) {
        gameBoardController.activateButtons(true,true,true,false);
    }




    @Override
    public void endTurn() {
        GameManager.getInstance().getGameBoard().activateButtons(true,true,false,true);
    }
}
