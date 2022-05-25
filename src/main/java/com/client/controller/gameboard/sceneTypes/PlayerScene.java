package com.client.controller.gameboard.sceneTypes;

import com.client.pane.game.GameBoard;
import com.client.game.Managers.GameManager;
import com.client.pane.game.space.purchasableSpace.SpaceDeed;

/**
 * Controls Player buttons events (for human players)
 */
public class PlayerScene implements  IPrepareScene{


    /**
     * Activates purchase button
     * @param space place user landed
     * @param gameBoardController main game controller
     */
    @Override
    public void notPurchased(SpaceDeed space, GameBoard gameBoardController) {
        gameBoardController.activateButtons(true,false,false,true);
        GameManager.getInstance().setDeed(space);


    }

    /**
     * Activate next turn button for the human player
     * @param gameBoardController main game controller
     */
    @Override
    public  void  nextTurnNormal(GameBoard gameBoardController){
        gameBoardController.activateButtons(false , true , true,true);

    }

    /**
     * Activate jail button  for the human player
     * @param gameBoardController main game controller
     */
    @Override
    public void nextTurnJail(GameBoard gameBoardController) {
        gameBoardController.activateButtons(true,true,true,false);
    }

    /**
     * Activate end turn button for the human player
     */
    @Override
    public void endTurn() {
        GameManager.getInstance().getGameBoard().activateButtons(true,true,false,true);
    }


}
