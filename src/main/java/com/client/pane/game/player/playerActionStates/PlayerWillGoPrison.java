package com.client.pane.game.player.playerActionStates;


import com.client.game.Managers.GameManager;
import com.client.pane.game.player.IPlayer;
import com.client.pane.game.space.ISpace;
import javafx.application.Platform;
import javafx.util.Pair;

public class PlayerWillGoPrison implements PlayerState {
    @Override
    public PlayerState Play(IPlayer player, int diceValue1, int diceValue2, ISpace space) {
        System.out.println("Player Will go prison Play");
        player.setNextTurn(true); // next player will play game
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
         return  new PlayerPrison();
    }




    @Override
    public PlayerState determineState(IPlayer player, int diceValue1, int diceValue2 ) {
        System.out.println("Player Will go prison determineState");

        return  new PlayerPrison(); // player in prison know
    }
}
