package com.client.pane.game.space.spaceCreation;


import com.client.pane.game.space.ISpace;


import com.client.pane.game.space.notPurchasableSpace.GoJail;
import com.client.pane.game.space.notPurchasableSpace.IncomeTax;
import com.client.pane.game.space.notPurchasableSpace.JailVisit;
import com.client.pane.game.space.notPurchasableSpace.StartingPoint;
import com.client.pane.game.space.purchasableSpace.Property;
import com.client.pane.game.space.purchasableSpace.RailFerrySpace;

import java.util.ArrayList;
import java.util.*;
/**
 * creation of the spaces according to the project rules with factory pattern
 */
public class NormalCreation implements   ISpaceCreatorFactory{



    private  List<GridCord> spaces = new ArrayList<>(); //**



    @Override
    public List<GridCord> createSpaces() { // create spaces

        LinkedHashMap<String,ArrayList> railFerry = new LinkedHashMap<>();
        LinkedHashMap<String,ArrayList> propertyNames = new LinkedHashMap<>();
        propertyNames.put("Istanbul",new ArrayList<Integer>(){{add(1);add(0);add(3);}}); // first value in array list is index of the space (for spacemanager) second value is x coordinate in game board third value is y coordinate in gameboard
        propertyNames.put("Ankara",new ArrayList<Integer>(){{add(2);add(0);add(2);}});
        propertyNames.put("Antalya",new ArrayList<Integer>(){{add(5);add(1);add(0);}});
        propertyNames.put("Muğla",new ArrayList<Integer>(){{add(6);add(2);add(0);}});
        propertyNames.put("Eskişehir",new ArrayList<Integer>(){{add(9);add(4);add(1);}});
        propertyNames.put("Aydın",new ArrayList<Integer>(){{add(10);add(4);add(2);}});
        propertyNames.put("Alanya",new ArrayList<Integer>(){{add(13);add(3);add(4);}});
        propertyNames.put("Denizli",new ArrayList<Integer>(){{add(14);add(2);add(4);}});

        railFerry.put("KaraTren",new ArrayList<Integer>(){{add(3);add(0);add(1);}});
        railFerry.put("TCDD",new ArrayList<Integer>(){{add(7);add(3);add(0);}});
        railFerry.put("Kadıköy Vapur",new ArrayList<Integer>(){{add(11);add(4);add(3);}});
        railFerry.put("Alsancak Vapur",new ArrayList<Integer>(){{add(15);add(1);add(4);}});

        return  setSpaces(propertyNames,railFerry);

    }

    private   List<GridCord> setSpaces(LinkedHashMap<String,ArrayList> propertyNames, LinkedHashMap<String,ArrayList>  railFerry){

        AddSpace(new StartingPoint(),0,4,0);
        AddSpace( new JailVisit(),0,0,4);
        AddSpace( new GoJail(),4,4,12);
        AddSpace( new IncomeTax(),4,0,8);
        AddProperties(propertyNames);
        AddRailFerry(railFerry);

        SetSpaceManager();
        List<GridCord> template = spaces;
        spaces = null; // for garbage collection
        return  template;

    }

    private  void  AddSpace(ISpace space,int xCordinate, int yCordinate,int position ){ // add space
        spaces.add(new GridCord(space,xCordinate,yCordinate,position));

    }



    private  void   SetSpaceManager(){ // sort spaces according to the index with bubble sort
        int n = spaces.size();
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (spaces.get(j).getPos() > spaces.get(j+1).getPos()) {
                    // swap arr[j+1] and arr[j]
                    GridCord temp = spaces.get(j);
                    spaces.set(j,spaces.get(j+1));
                    spaces.set(j+1,temp);

                }





    }
    private  void AddProperties(LinkedHashMap<String,ArrayList> propertyNames){ // add properties

        Set<String> keySet = propertyNames.keySet();
        ArrayList<String> listOfKeys = new ArrayList<String>(keySet);
        int count = listOfKeys.size();
        String name;
        int index;
        for(int i= 0; i < count ; i++ ){
            name = listOfKeys.get(i);
            ArrayList<Integer> values = propertyNames.get(name);
            index =  (int) values.get(0);
            ISpace property = new Property(name, setPrice(i));
            AddSpace( property,(int)values.get(1),(int)values.get(2),(int) values.get(0));



        }
    }

    /**
     *
     *      * @param indexset price of the property spaces
     *
            initial cost of properties
     */
    private  int setPrice(int index){ //
        int initialCost = 100; //
        int price = 100 + index * 60;
        if(price > 500){
            price = 500;
        }
        return  price;
    }


    /**
     * add railway ferry port space
     *
     */

    private  void  AddRailFerry(LinkedHashMap<String,ArrayList> railFerryInfo ){ //

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
            ISpace railFerry = new RailFerrySpace(name, initialCost);
            AddSpace( railFerry,(int)values.get(1),(int)values.get(2),(int) values.get(0));


        }


    }

    /**
     * inner class for returning spaces with position and coordinates
     */

    public  class  GridCord{
        int pos;
        int xCor;
        int yCor;
        ISpace space;


        public  int getPos(){
            return pos;
        }
        public  int getxCor(){
            return  xCor;
        }

        public int getyCor(){
            return  yCor;
        }
        public  ISpace getSpace(){
            return  space;
        }

        private   GridCord(ISpace space,int xCordinate, int yCordinate,int position){
            this.space = space;
            this.xCor = xCordinate;
            this.yCor = yCordinate;
            this.pos = position;
        }
    }



}