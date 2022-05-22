package com.client.pane.game;

import com.client.game.Managers.GameManager;

public class BotScene implements IPrepareScene{




    @Override
    public void playerWaitRoll(GameBoard gameBoard) {
        gameBoard.roll();
        gameBoard.activateButtons(true , true , true);
    }
    @Override
    public void playerWaitPurchasable(GameBoard gameBoard) {
        gameBoard.purchase();
    }

    @Override
    public void PlayerWaitNotPurchasable(GameBoard gameBoard) {

        playerEnd(gameBoard);
        gameBoard.endTurn();
    }

    @Override
    public void playerEnd(GameBoard gameBoard) {

        gameBoard.activateButtons(false , true , true);

    }
}
