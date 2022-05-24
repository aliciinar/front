package com.client.pane.game.player.playerActionStates;


import com.client.game.Managers.GameManager;
import com.client.pane.game.player.IPlayer;
import com.client.pane.game.space.ISpace;
import javafx.util.Pair;

public class PlayerNotPrison implements PlayerState{

    private int diceThrow;

    public PlayerNotPrison(){
        diceThrow = 3;


    }

    @Override
    public PlayerState Play(IPlayer player , int diceValue1, int diceValue2, ISpace space) {

        if(diceValue1 == diceValue2){// if diceValue1 roll equal to this roll value player check whether player roll again or go jail
            diceThrow -=1;
            if(diceThrow == 0){ // player should go to prison
              //  Pair<PlayerState,Boolean> response = new Pair<PlayerState,Boolean>(new PlayerWillGoPrison(),false);
                // set player to jail
                GameManager.getInstance().goJail();
                System.out.println("go jail");
                player.checkFalseMoneyIncrease(true);
                return  new PlayerWillGoPrison();

            }else{ // player should roll again
             //   Pair<PlayerState,Boolean> response = new Pair<PlayerState,Boolean>(this,false);
                player.setNextTurn(false);
                System.out.println("rollllll aaaagaaaiiiiin");

                space.action(player);
                if(space.getName().equals("Go To Jail")){
                    System.out.println("JailDeyimmmm ------------------");
                    player.setNextTurn(true); // player in jail so turn of next player
                   // response = new Pair<PlayerState,Boolean>(new PlayerPrison(),false);
                 //   System.out.println("stateim "+ response.getKey());;
                    player.checkFalseMoneyIncrease(false);
                    return  new PlayerPrison();
                }else{
                    player.checkFalseMoneyIncrease(false);

                    return  this;
                }

            }

        }else{
            diceThrow = 3;

            // do nothing
            space.action(player); // action in space
            System.out.println("action in space");
            player.setNextTurn(true);
            System.out.println("space adııı" + space.getName());
            if(space.getName().equals("Go To Jail")){
                System.out.println("JailDeyimmmm ------------------");
               // Pair<PlayerState,Boolean> response = new Pair<PlayerState,Boolean>(new PlayerPrison(),false);
             //   System.out.println("stateim "+ response.getKey());;
                return  new PlayerPrison();
            }
        //    Pair<PlayerState,Boolean> response = new Pair<PlayerState,Boolean>(this,false);
            return  this;
        }

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


            return  this;
        }
    }


}
