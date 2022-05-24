package com.client.game.Managers;

import com.client.pane.game.space.ISpace;
import com.client.controller.gameboard.BoardSpace;
import com.client.pane.game.space.SpaceCreation.ISpaceCreatorFactory;
import com.client.pane.game.space.SpaceCreation.NormalCreation;

;import java.util.ArrayList;
import java.util.List;

public class SpaceManager {

    private  static  SpaceManager instance = null;
    private List<BoardSpace>  boardSpaces = new ArrayList<>();
    public  static  synchronized  SpaceManager getInstance(){
        if(instance == null){
            instance = new SpaceManager();
        }
        return  instance;
    }

    public  void  setSpaces(List<BoardSpace>  boardSpaces){
        this.boardSpaces = boardSpaces;

    }

    public ISpace getSpace(int i){
        return  boardSpaces.get(i).getSpace();
    }

    public  List<NormalCreation.GridCord> createSpaces(ISpaceCreatorFactory spaceCreatorFactory){
         return    spaceCreatorFactory.createSpaces();
    }



}
