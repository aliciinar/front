package com.client.controller.gameboard.sceneTypes;

import com.client.pane.game.GameBoard;
import com.client.game.Managers.GameManager;
import com.client.pane.game.space.purchasableSpace.SpaceDeed;

/**
 * Controls the bot gui events.
 */
public class BotScene implements IPrepareScene{


    /**
     * This function is used for gui event if bot needs to activate purchase button which is not necessary.
     * @param space purchased space
     * @param gameBoardController main controller for the game
     */
    @Override
    public void notPurchased(SpaceDeed space, GameBoard gameBoardController) {

    }


    /**
     *  prepare scne for the bot
     * @param gameBoardController main controller of the game.
     */
    @Override
    public void nextTurnNormal(GameBoard gameBoardController) {
        gameBoardController.activateButtons(true , true , true,true);
        gameBoardController.roll();


    }

    /**
     * Activates jail button in the game screen.
     * @param gameBoardController main controller of the game.
     */
    @Override
    public void nextTurnJail(GameBoard gameBoardController) {
        gameBoardController.activateButtons(true,true,true,false);
    }


    /**
     * Activates end turn button in the game screen.
     */
    @Override
    public void endTurn() {
        GameManager.getInstance().getGameBoard().activateButtons(true,true,false,true);
    }
}
