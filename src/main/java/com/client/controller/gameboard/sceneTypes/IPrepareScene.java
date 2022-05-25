package com.client.controller.gameboard.sceneTypes;

import com.client.pane.game.GameBoard;
import com.client.pane.game.space.purchasableSpace.SpaceDeed;

/**
 * Controls button events for game.
 * we are determining scene types according to the player type and prepare scene in gameBoard.
 */
public interface IPrepareScene {


    /**
     * player on deed space so prepare purchase scene
     * @param space current Space
     * @param gameBoardController
     */
    void  notPurchased(SpaceDeed space, GameBoard gameBoardController);

    /**
     * prepare scene according to the active player
     * @param gameBoardController
     */
    void  nextTurnNormal(GameBoard gameBoardController);

    /**
     *  player in prison so jail time button will open
     * @param gameBoardController
     */
    void  nextTurnJail(GameBoard gameBoardController);

    /**
     * prepare end turn button since play is finished
     */
    void  endTurn();


}
