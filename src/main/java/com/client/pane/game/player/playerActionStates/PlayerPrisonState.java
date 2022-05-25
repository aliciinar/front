package com.client.pane.game.player.playerActionStates;

import com.client.pane.game.player.IPlayer;
import com.client.pane.game.space.ISpace;

public class PlayerPrisonState implements IPlayerState {
    // player in prison state.

    private int prisonTime ;  // player will be in prison 2 round

    public PlayerPrisonState(){
        prisonTime = 2;

    }
    @Override
    public IPlayerState Play(IPlayer player, int diceValue1, int diceValue2 , ISpace space) { //  player in prison so wait this turn  in prison

       if(prisonTime == 1){ // player leave the jail next round
           IPlayerState IPlayerState = new PlayerNotPrisonState();
           player.setPrison(false);
           return IPlayerState;
       }else{
           prisonTime = prisonTime - 1 ;

           return  this;
       }

    }





    @Override
    public IPlayerState determineState(IPlayer player, int diceValue1, int diceValue2) {
        System.out.println("Player prison determineState");
        // todo burdaki parentez kalkabilir
        /*int prisTime = prisonTime -1;
        if(prisTime == 0 ){
            player.setPrison(false);
            player.setNextTurn(true);
            return  new PlayerNotPrison();
        }*/
        return  this;

    }


}
