package com.client.pane.game;

public class PlayerScene implements  IPrepareScene{



    @Override
    public void playerWaitRoll(GameBoard gameBoard) {

        gameBoard.activateButtons(false , true , true);
    }

    @Override
    public void playerWaitPurchasable(GameBoard gameBoard) {
        gameBoard.activateButtons(true , false , false);
    }

    @Override
    public void PlayerWaitNotPurchasable(GameBoard gameBoard) {
        gameBoard.activateButtons(true , true , false);
    }


    @Override
    public void playerEnd(GameBoard gameBoard) {

        gameBoard.activateButtons(true , true , true);
    }
}
