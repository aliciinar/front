package com.client.pane.game.player.playerActionStates;


import com.client.game.Managers.GameManager;
import com.client.pane.game.player.IPlayer;
import com.client.pane.game.space.ISpace;

public class PlayerNotPrisonState implements IPlayerState {
    // player not in prison. Player can roll double and in this state we determine player will play again or not
    private int diceThrow; // player will have 3 diceThrow. If player roll double it will increase 1
                            // if diceThrow equal to 0 player will go jail

    public PlayerNotPrisonState(){
        diceThrow = 3;


    }

    @Override
    public IPlayerState Play(IPlayer player , int diceValue1, int diceValue2, ISpace space) {

        if(diceValue1 == diceValue2){// if diceValue1 roll equal to this roll value player check whether player roll again or go jail
            diceThrow -=1;
            if(diceThrow == 0){ // player should go to prison
                GameManager.getInstance().goJail(); // player should go jail in GUI
                player.checkFalseMoneyIncrease(true);
                return  new PlayerWillGoPrisonState(); // player state is not player will go prison

            }else{ // player roll double so player will play again
                player.setNextTurn(false); // player will go play again in next turn
                space.action(player); //  do action in the space
                if(space.getName().equals("Go To Jail")){ // if space is jail space it shouldnt go jail again. We use thread for GUI and we must check otherwise we have thread problems.
                    player.setNextTurn(true); // player in jail so turn of next player
                    player.checkFalseMoneyIncrease(false);
                    return  new PlayerPrisonState();
                }else{
                    player.checkFalseMoneyIncrease(false);
                    return  this;
                }

            }

        }else{ // player is not roll double. do action in space
            diceThrow = 3;

            // do nothing
            space.action(player); // action in space
            player.setNextTurn(true);
            if(space.getName().equals("Go To Jail")){ // if space is jail space player should change state

                return  new PlayerPrisonState();
            }
            return  this;
        }

    }



    @Override
    public IPlayerState determineState(IPlayer player, int diceValue1, int diceValue2) {
        int diceValue = diceThrow;
        if(diceValue1 == diceValue2){// if diceValue1 roll equal to this roll value player check whether player roll again or go jail
            diceValue -=1;
            if(diceValue == 0){ // player should go to prison
                player.setPrison(true);
                return  new PlayerWillGoPrisonState();

            }else{ // player should roll again

                return  this;
            }

        }else{ // normal play


            return  this;
        }
    }


}
