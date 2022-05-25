package com.client.game.Managers;

import com.client.controller.observer.IObserverText;

import com.client.pane.game.space.ISpace;
import com.client.pane.game.BoardSpace;
import com.client.pane.game.space.spaceCreation.ISpaceCreatorFactory;
import com.client.pane.game.space.spaceCreation.NormalCreation;

;import java.util.ArrayList;
import java.util.List;



public class SpaceManager {
    /**
     * This class mainly connects spaces from to gui to Ispaces class
     */
    private  static  SpaceManager instance = null;
    private List<BoardSpace> boardSpaceControllers = new ArrayList<>();
    public  static  synchronized  SpaceManager getInstance(){
        if(instance == null){
            instance = new SpaceManager();
        }
        return  instance;
    }

    public  void  setSpaces(List<BoardSpace> boardSpaceControllers){
        this.boardSpaceControllers = boardSpaceControllers;

    }

    public ISpace getSpace(int i){
        return  boardSpaceControllers.get(i).getSpace();
    }

    public  List<NormalCreation.GridCord> createSpaces(ISpaceCreatorFactory spaceCreatorFactory){
         return    spaceCreatorFactory.createSpaces();
    }


    public IObserverText getBoardSpace(ISpace space){
        for (BoardSpace board: boardSpaceControllers) {
            if(space.equals(board.getSpace()))
            return  board;
        }
        return  null;
    }


}
