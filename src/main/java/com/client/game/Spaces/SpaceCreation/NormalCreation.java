package com.client.game.Spaces.SpaceCreation;

import com.client.game.Managers.SpaceManager;
import com.client.game.Spaces.ISpace;
import com.client.game.Spaces.NotPurchasableSpace.GoJail;
import com.client.game.Spaces.NotPurchasableSpace.IncomeTax;
import com.client.game.Spaces.NotPurchasableSpace.JailVisit;
import com.client.game.Spaces.NotPurchasableSpace.StartingPoint;

import com.client.game.Spaces.PurchasableSpace.Property;
import com.client.game.Spaces.PurchasableSpace.RailRoad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;

public class NormalCreation extends  CreationStrategy{


    public  void setSpaces(HashMap<String,Integer> propertyNames, HashMap<String,Integer>  railRoadtNames,HashMap<String,Integer>  ferryPortNames){


        HashMap<Integer, ISpace> spaces = new HashMap<>();// hash map for storing spaces
        ISpace startingPoint = new StartingPoint("Starting Cell",0); // starting Point
        spaces.put(0,startingPoint);
        ISpace jailVisit = new JailVisit("Jail/JailVisit",4); // jail Visit
        spaces.put(4,jailVisit);
        ISpace goJail = new GoJail("GoJail",12); // jail
        spaces.put(12,goJail);
        ISpace incomeTax = new IncomeTax("IncomeTax",3);
        spaces.put(3,incomeTax);
        AddProperties(propertyNames,spaces);
        AddRailRoad(railRoadtNames,spaces);
        AddFerryPort(ferryPortNames,spaces);


        ISpace[] spacesArray = new ISpace[16];
        for(int i= 0; i < 16 ; i++ ){
            spacesArray[i] = spaces.get(i);
        }
        SpaceManager.getInstance().SetSpaces(spacesArray);
    }
    private  void AddProperties(HashMap<String,Integer> propertyNames,HashMap<Integer,ISpace> spaces){

        Set<String> keySet = propertyNames.keySet();
        ArrayList<String> listOfKeys = new ArrayList<String>(keySet);
        int count = listOfKeys.size();
        int initialCost = 100; // initial cost of properties
        String name;
        int index;
        for(int i= 0; i < count ; i++ ){
             name = listOfKeys.get(i);
             index =   propertyNames.get(name);
            ISpace property = new Property(name, initialCost + i*57 , index);
            spaces.put(index,property);
        }
    }




    private  void  AddRailRoad(HashMap<String,Integer> railRoadtNames ,HashMap<Integer,ISpace> spaces){

        Set<String> keySet = railRoadtNames.keySet();
        ArrayList<String> listOfKeys = new ArrayList<String>(keySet);
        int count = listOfKeys.size();
        int initialCost = 250; // initial cost of properties
        String name;
        int index;
        for(int i= 0; i < count ; i++ ){
            name = listOfKeys.get(i);
            index =   railRoadtNames.get(name);
            ISpace railRoad = new RailRoad(name, initialCost,index);
            spaces.put(index,railRoad);
        }


    }

    private  void  AddFerryPort(HashMap<String,Integer> ferryPortNames ,HashMap<Integer,ISpace> spaces){
        Set<String> keySet = ferryPortNames.keySet();
        ArrayList<String> listOfKeys = new ArrayList<String>(keySet);
        int count = listOfKeys.size();
        int initialCost = 250; // initial cost of properties
        String name;
        int index;
        for(int i= 0; i < count ; i++ ){
            name = listOfKeys.get(i);
            index =   ferryPortNames.get(name);
            ISpace feryPort = new RailRoad(name, initialCost,index);
            spaces.put(index,feryPort);
        }
    }






}
