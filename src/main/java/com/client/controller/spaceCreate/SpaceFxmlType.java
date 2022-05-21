package com.client.controller.spaceCreate;

import com.client.game.Spaces.ISpace;
import javafx.scene.layout.Pane;

public abstract class SpaceFxmlType {

    protected  int xCordinate;
    protected  int yCordinate;

    public abstract Pane createSpace(ISpace space);
    public void  setGridCordinations(int x, int y){
        this.xCordinate = x;
        this.yCordinate = y;
    }

    public  int getxCordinate(){
            return  xCordinate;
    }

    public  int getyCordinate(){
        return  yCordinate;
    }
}
