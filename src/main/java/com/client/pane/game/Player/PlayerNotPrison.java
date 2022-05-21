package com.client.pane.game.Player;


public class PlayerNotPrison implements PlayerState{

    private int diceThrow;

    PlayerNotPrison(){
        diceThrow = 2;
    }

    @Override
    public boolean Play(IPlayer player ) {

        if(diceThrow == 0) return false;

        int[] dices = new int[2];

        dices[1] = 1;
        dices[0] = 1;

        if (dices[1] == 0) {
            diceThrow = diceThrow - 1;
            return true ;
        }
        else {
            return false ;
        }

    }

    @Override
    public PlayerState EndPlay(){
        System.out.println(diceThrow);
        if (diceThrow > 0) {
            diceThrow = 2 ;
            return this ;
        }
        else return new PlayerPrison() ;
    }




}
