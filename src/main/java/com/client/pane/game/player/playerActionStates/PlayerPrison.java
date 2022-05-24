package com.client.pane.game.player.playerActionStates;

import com.client.pane.game.player.IPlayer;
import com.client.pane.game.space.ISpace;
import javafx.util.Pair;

public class PlayerPrison implements PlayerState{

    private int prisonTime ;

    public  PlayerPrison(){
        prisonTime = 2;

    }
    @Override
    public PlayerState Play(IPlayer player, int diceValue1, int diceValue2 , ISpace space) {
        System.out.println("---------Player Prison Play----------");
        System.out.println("Player Namee : " + player.getName());
       // return EndPlay();
       if(prisonTime == 1){ // player leave the jail next round
           System.out.println("Leave the jail");
         //  GameManager.getInstance().nextTurn();
           PlayerState playerState = new PlayerNotPrison();
        //   PlayerState,Boolean> response = new Pair<>(playerState, true);
           player.setPrison(false);
           return  playerState;
       }else{
           System.out.println("Still In Jail");
           System.out.println("Player hapishane günlükleriiiiii " + player.getName() + "kalan gün " + prisonTime);
           prisonTime = prisonTime - 1 ;

        //   Pair<PlayerState,Boolean> response = new Pair<>(this, false);
           return  this;
       }

    }





    @Override
    public PlayerState determineState(IPlayer player, int diceValue1, int diceValue2) {
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
