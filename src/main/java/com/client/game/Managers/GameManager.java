package com.client.game.Managers;

import com.client.pane.game.Player.Player;


import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private  static  GameManager instance = null;
    List<Player> players = new ArrayList<Player>();
    int playerNumber;
    public  static  synchronized  GameManager getInstance(){
        if(instance == null){
            instance = new GameManager();
        }
        return  instance;

    }

    public  void  setPlayers(List<Player> players){
        this.players = players;
        playerNumber = players.size();
    }

    public  void  game(){
        int playerIndex = 0;
        while (true){
           if( players.get(playerIndex).turn()){
               playerIndex +=1;
               playerIndex %= playerNumber;
           }
           else{
               System.out.println("Game oVer");
           }

        }

    }


}
