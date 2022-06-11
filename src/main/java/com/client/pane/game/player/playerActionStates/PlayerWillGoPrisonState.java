package com.client.pane.game.player.playerActionStates;


import com.client.game.Managers.GameManager;
import com.client.pane.game.player.IPlayer;
import com.client.pane.game.space.ISpace;
import javafx.application.Platform;

/**
 * player roll double three time so player will go prison
 * this state is just move player to the jail. and after that players new state will player prison state
 */
public class PlayerWillGoPrisonState implements IPlayerState {
    @Override
    public IPlayerState Play(IPlayer player, int diceValue1, int diceValue2, ISpace space) {
        player.setNextTurn(true);
        player.setPrison(true);

        Thread taskThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 1 ; i++) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {

                    }

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            GameManager.getInstance().goJail(); // go to jail

                        }
                    });
                }


              //  GameManager.getInstance().nextTurn(); // go to jail



            }
        });





        taskThread.start();

      //   Pair<PlayerState,Boolean> response = new Pair<>(new PlayerPrison(),false); // player in prison know
         return  new PlayerPrisonState();
    }




    @Override
    public IPlayerState determineState(IPlayer player, int diceValue1, int diceValue2 ) {

        return  new PlayerPrisonState(); // player in prison know
    }
}
