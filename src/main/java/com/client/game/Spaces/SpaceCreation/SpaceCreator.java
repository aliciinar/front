package com.client.game.Spaces.SpaceCreation;

import java.util.HashMap;

public class SpaceCreator {

   private  CreationStrategy strategy;

    public  SpaceCreator(CreationStrategy strategy){

       // strategy.setSpaces(propertyNames,railRoadNames,ferryPortNames);
        this.strategy = strategy;

    }

    public  void  CreateSpaces(){

        HashMap<String,Integer> cities = new HashMap<>();
        cities.put("Istanbul",1);
        cities.put("Ankara",2);
        cities.put("Antalya",6);
        cities.put("Muğla",7);
        cities.put("Eskişehir",9);
        cities.put("Aydın",10);
        cities.put("Alanya",14);
        cities.put("Denizli",15);

        HashMap<String,Integer> railRoads = new HashMap<>();
        railRoads.put("KaraTren",5);
        railRoads.put("TCDD",11);

        HashMap<String,Integer> ferryPorts = new HashMap<>();
        ferryPorts.put("KadıKöy",8);
        ferryPorts.put("Alsancak",13);
        strategy.setSpaces(cities,railRoads,ferryPorts);


    }
}
