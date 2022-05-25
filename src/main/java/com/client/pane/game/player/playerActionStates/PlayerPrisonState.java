package com.client.pane.game.player.playerActionStates;

import com.client.pane.game.player.IPlayer;
import com.client.pane.game.space.ISpace;

/**
 * player in prison state.
 */
public class PlayerPrisonState implements IPlayerState {
    //

    private int prisonTime ;  // player will be in prison 2 round

    public PlayerPrisonState(){
        prisonTime = 2;

    }

    /**
     * player in prison so wait this turn  in prison
     * @param player current player
     * @param diceValue1
     * @param diceValue2
     * @param space current space
     * @return  new state
     */
    @Override
    public IPlayerState Play(IPlayer player, int diceValue1, int diceValue2 , ISpace space) {

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


        return  this;

    }


}
