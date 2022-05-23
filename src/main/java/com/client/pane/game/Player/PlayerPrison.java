package com.client.pane.game.Player;

import com.client.game.Managers.GameManager;
import com.client.pane.game.space.ISpace;
import javafx.util.Pair;

public class PlayerPrison implements PlayerState{

    private int prisonTime ;
    PlayerPrison(){
        prisonTime = 2;

    }
    @Override
    public Pair<PlayerState,Boolean> Play(IPlayer player, int diceValue1, int diceValue2 , ISpace space) {
        System.out.println("---------Player Prison Play----------");
        System.out.println("Player Namee : " + player.getName());
       // return EndPlay();
       if(prisonTime == 0){ // player leave the jail next round
           System.out.println("Leave the jail");
         //  GameManager.getInstance().nextTurn();
           PlayerState playerState = new PlayerNotPrison();
           Pair<PlayerState,Boolean> response = new Pair<>(playerState, true);
           player.setPrison(false);
           return  response;
       }else{
           System.out.println("Still In Jail");
           System.out.println("Player hapishane günlükleriiiiii " + player.getName() + "kalan gün " + prisonTime);
           prisonTime = prisonTime - 1 ;

           Pair<PlayerState,Boolean> response = new Pair<>(this, false);
           return  response;
       }

    }



    @Override
    public PlayerState EndPlay() {
        if (prisonTime == 0) return new PlayerNotPrison();
        else return this;
    }



    @Override
    public PlayerState determineState(IPlayer player, int diceValue1, int diceValue2) {
        System.out.println("Player prison determineState");
        return  this;

    }


}
