package com.client.pane.game;

import com.client.game.Managers.GameManager;
import com.client.pane.game.space.PurchasableSpace.SpaceDeed;

public class BotScene implements IPrepareScene{


    @Override
    public void notPurchased(SpaceDeed space) {
        System.out.println("Bot satın alma");
    }

    @Override
    public void normal() {
        System.out.println("normal");
    }

    @Override
    public void nextTurnNormal(GameBoard gameBoard) {
        gameBoard.activateButtons(true , true , true);
        gameBoard.roll();
        System.out.println("nextTurnNormal");

    }

    @Override
    public void nextTurnJail(GameBoard gameBoard) {
        System.out.println("botnextTurnJail");
    }


    @Override
    public void playerEnd(GameBoard gameBoard) {

        gameBoard.activateButtons(false , true , true);

    }

    @Override
    public void endTurn() {
        GameManager.getInstance().getGameBoard().activateButtons(true,true,false);
    }
}
