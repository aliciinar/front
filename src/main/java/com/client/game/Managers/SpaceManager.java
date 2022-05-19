package com.client.game.Managers;

import com.frontend.Spaces.ISpace;

public class SpaceManager {

    private  static  SpaceManager instance = null;
    private ISpace[] ISpaces = new ISpace[20];
    public  static  synchronized  SpaceManager getInstance(){
        if(instance == null){
            instance = new SpaceManager();
        }
        return  instance;
    }

    public  void  SetSpaces(ISpace[] ISpaces){
        this.ISpaces = ISpaces;

    }

}
