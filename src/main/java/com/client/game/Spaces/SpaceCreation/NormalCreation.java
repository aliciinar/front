package com.client.game.Spaces.SpaceCreation;

import com.client.controller.spaceCreate.*;
import com.client.game.Spaces.ISpace;
import com.client.game.Spaces.NotPurchasableSpace.GoJail;
import com.client.game.Spaces.NotPurchasableSpace.IncomeTax;
import com.client.game.Spaces.NotPurchasableSpace.JailVisit;
import com.client.game.Spaces.NotPurchasableSpace.StartingPoint;

import com.client.game.Spaces.PurchasableSpace.Property;
import com.client.game.Spaces.PurchasableSpace.RailFerrySpace;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;

public class NormalCreation implements   ISpaceCreatorFactory{

    private  HashMap<ISpace, SpaceFxmlType> fxmlInformations = new HashMap<>();
    private  HashMap<Integer, ISpace> spaces = new HashMap<>();



    @Override
    public Pair<ISpace[] , HashMap<ISpace, SpaceFxmlType>> createSpaces() {

        HashMap<String,ArrayList> railFerry = new HashMap<>();
        HashMap<String,ArrayList> propertyNames = new HashMap<>();
        propertyNames.put("Istanbul",new ArrayList<Integer>(){{add(1);add(0);add(3);}});
        propertyNames.put("Ankara",new ArrayList<Integer>(){{add(2);add(0);add(2);}});
        propertyNames.put("Antalya",new ArrayList<Integer>(){{add(5);add(1);add(0);}});
        propertyNames.put("Muğla",new ArrayList<Integer>(){{add(6);add(2);add(0);}});
        propertyNames.put("Eskişehir",new ArrayList<Integer>(){{add(9);add(4);add(1);}});
        propertyNames.put("Aydın",new ArrayList<Integer>(){{add(10);add(4);add(2);}});
        propertyNames.put("Alanya",new ArrayList<Integer>(){{add(13);add(3);add(4);}});
        propertyNames.put("Denizli",new ArrayList<Integer>(){{add(14);add(2);add(4);}});

        railFerry.put("KaraTren",new ArrayList<Integer>(){{add(3);add(0);add(3);}});
        railFerry.put("TCDD",new ArrayList<Integer>(){{add(11);add(0);add(3);}});
        railFerry.put("KadıKöy",new ArrayList<Integer>(){{add(7);add(0);add(3);}});
        railFerry.put("Alsancak",new ArrayList<Integer>(){{add(15);add(0);add(3);}});

        return  setSpaces(propertyNames,railFerry);

    }

    private   Pair<ISpace[] , HashMap<ISpace, SpaceFxmlType>> setSpaces(HashMap<String,ArrayList> propertyNames, HashMap<String,ArrayList>  railFerry){


        AddSpace(new StartingPoint("Starting Cell",0),0,4,new NormalSpaceController());
        AddSpace( new JailVisit("Jail/JailVisit",4),0,0,new NormalSpaceController());
        AddSpace( new GoJail("GoJail",12),4,4,new NormalSpaceController());
        AddSpace( new IncomeTax("IncomeTax",8),4,0,new NormalSpaceController());
        AddProperties(propertyNames);
        AddRailFerry(railFerry);
        ISpace[] spacesArray = new ISpace[16];
        spacesArray = SetSpaceManager();
        HashMap<ISpace, SpaceFxmlType> fxmlInformationsStack =  fxmlInformations;
        fxmlInformations = null;
        spaces = null;
        return  new Pair<ISpace[] , HashMap<ISpace, SpaceFxmlType>> (spacesArray,fxmlInformationsStack);
    }

    private  void  AddSpace(ISpace space,int xCordinate, int yCordinate, SpaceFxmlType fxmlType ){
        spaces.put(space.GetPosition(),space);
        SetFxmlType(fxmlType,xCordinate,yCordinate,space);
    }

    private  void  SetFxmlType(SpaceFxmlType fxmlType,int x,int y,ISpace space){
        fxmlType.setGridCordinations(x,y);
        fxmlInformations.put(space,fxmlType);
    }

    private  ISpace[]   SetSpaceManager(){
        ISpace[] spacesArray = new ISpace[16];
        for(int i= 0; i < 16 ; i++ ){
            spacesArray[i] = spaces.get(i);
        }
        return  spacesArray;

    }
    private  void AddProperties(HashMap<String,ArrayList> propertyNames){

        Set<String> keySet = propertyNames.keySet();
        ArrayList<String> listOfKeys = new ArrayList<String>(keySet);
        int count = listOfKeys.size();
        int initialCost = 100; // initial cost of properties
        String name;
        int index;
        for(int i= 0; i < count ; i++ ){
            name = listOfKeys.get(i);
            ArrayList<Integer> values = propertyNames.get(name);
            index =  (int) values.get(0);
            ISpace property = new Property(name, initialCost + i*57 , index);
            AddSpace( property,(int)values.get(1),(int)values.get(2),new PropertyInit());



        }
    }




    private  void  AddRailFerry(HashMap<String,ArrayList> railFerryInfo ){

        Set<String> keySet = railFerryInfo.keySet();
        ArrayList<String> listOfKeys = new ArrayList<String>(keySet);
        int count = listOfKeys.size();
        int initialCost = 250; // initial cost of properties
        String name;
        int index;
        for(int i= 0; i < count ; i++ ){
            name = listOfKeys.get(i);
            ArrayList<Integer> values = railFerryInfo.get(name);
            index = (int)  values.get(0);
            ISpace railFerry = new RailFerrySpace(name, initialCost,index);
            AddSpace( railFerry,(int)values.get(1),(int)values.get(2),new FerryRailRoadSpaceController());


        }


    }



}