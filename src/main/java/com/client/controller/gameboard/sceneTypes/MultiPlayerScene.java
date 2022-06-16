package com.client.controller.gameboard.sceneTypes;

import com.client.game.Managers.GameManager;
import com.client.pane.game.GameBoard;
import com.client.pane.game.space.purchasableSpace.SpaceDeed;

public class MultiPlayerScene implements IPrepareScene{



    /**
     * This function is used for gui event if bot needs to activate purchase button which is not necessary.
     * @param space purchased space
     * @param gameBoardController main controller for the game
     */

    @Override
    public void notPurchased(SpaceDeed space, GameBoard gameBoardController) {
        GameManager.getInstance().setDeed(space);

    }

    /**
     *  prepare scne for the bot
     * @param gameBoardController main controller of the game.
     */
    @Override
    public void nextTurnNormal(GameBoard gameBoardController) {
        gameBoardController.activateButtons(true , true , true,true);
       // gameBoardController.roll();
        // todo start request of info from server


    }

    @Override
    public void nextTurnJail(GameBoard gameBoardController) {
        gameBoardController.activateButtons(true , true , true,true);
        // todo wait end turn
    }

    @Override
    public void endTurn() {
        GameManager.getInstance().getGameBoard().activateButtons(true , true , true,true);
    }
}
