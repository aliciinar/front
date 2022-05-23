package com.client.pane.game.Player;


import com.client.game.Managers.GameManager;
import com.client.pane.game.space.ISpace;
import javafx.util.Pair;

public class PlayerNotPrison implements PlayerState{

    private int diceThrow;

    PlayerNotPrison(){
        diceThrow = 3;

    }

    @Override
    public Pair<PlayerState,Boolean> Play(IPlayer player , int diceValue1, int diceValue2, ISpace space) {

        if(diceValue1 == diceValue2){// if diceValue1 roll equal to this roll value player check whether player roll again or go jail
            diceThrow -=1;
            if(diceThrow == 0){ // player should go to prison
                Pair<PlayerState,Boolean> response = new Pair<PlayerState,Boolean>(new PlayerWillGoPrison(),false);
                // set player to jail
                GameManager.getInstance().goJail();
                System.out.println("go jail");
                return  response;

            }else{ // player should roll again
                Pair<PlayerState,Boolean> response = new Pair<PlayerState,Boolean>(this,false);
                // player roll again
                player.setNextTurn(false);
                System.out.println("rollllll aaaagaaaiiiiin");
                space.action(player);
                return  response;
            }

        }else{
            diceThrow = 3;

            // do nothing
            space.action(player); // action in space
            System.out.println("action in space");
            player.setNextTurn(true);
            if(space.getName().equals("Go To Jail")){
                Pair<PlayerState,Boolean> response = new Pair<PlayerState,Boolean>(new PlayerPrison(),false);
                return  response;
            }
            Pair<PlayerState,Boolean> response = new Pair<PlayerState,Boolean>(this,false);
            return  response;
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


    @Override
    public PlayerState determineState(IPlayer player, int diceValue1, int diceValue2) {
        int diceValue = diceThrow;
        if(diceValue1 == diceValue2){// if diceValue1 roll equal to this roll value player check whether player roll again or go jail
            diceValue -=1;
            if(diceValue == 0){ // player should go to prison
                System.out.println("Player Not Prison determineState player will go prison");
                player.setPrison(true);
                return  new PlayerWillGoPrison();

            }else{ // player should roll again

                return  this;
            }

        }else{ // normal play
            diceValue = 3;

            return  this;
        }
    }


}
