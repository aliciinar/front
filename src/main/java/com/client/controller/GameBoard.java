package com.client.controller;

import com.client.controller.spaceCreate.SpaceFxmlType;
import com.client.game.Managers.SpaceManager;
import com.client.game.Spaces.ISpace;
import com.client.game.Spaces.PurchasableSpace.Property;
import com.client.game.Spaces.SpaceCreation.ISpaceCreatorFactory;
import com.client.game.Spaces.SpaceCreation.NormalCreation;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.util.Pair;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

import java.util.HashMap;
import java.util.Map;

public class GameBoard {

   @FXML
   GridPane gameBoardGrid;

    @FXML
    ImageView player1;

    @FXML
    ImageView player2;


    public  void  Move(int index,int lastindex){

           if(lastindex < 4){

           }
           else if(lastindex < 8 ){

           }
           else if(lastindex < 12){

           }
           else{

           }


    }


    private  void  MoveUp(ImageView player,int multiplier){

    }

   private  void  MoveRight(ImageView player,int multiplier){

   }

   private  void  MoveLeft(ImageView player,int multiplier){

   }

   private  void  MoveDown(ImageView player,int multiplier){

   }


    @FXML
    public void initialize() {

        //  SpaceCreatorFactory createSpaces = new SpaceCreatorFactory(new NormalCreation());
        //  HashMap<ISpace, SpaceFxmlType>  spaces = createSpaces.createSpaces();
        // int spaceCount = spaces.length;



        /*ISpaceCreatorFactory normalCreation = new NormalCreation();
        Pair<ISpace[], HashMap<ISpace, SpaceFxmlType>> spaceInformations = normalCreation.createSpaces();
        SpaceManager.getInstance().SetSpaces(spaceInformations.getKey());
        HashMap<ISpace, SpaceFxmlType> spaces = spaceInformations.getValue();
        for (Map.Entry<ISpace, SpaceFxmlType> entry : spaces.entrySet()) {
            ISpace space = entry.getKey();
            if (space instanceof Property) {
                System.out.println(space.getName());
                SpaceFxmlType fxmlType = entry.getValue();
                Pane pane = fxmlType.createSpace(space);
                System.out.println("x" + fxmlType.getxCordinate());
                System.out.println("y" + fxmlType.getyCordinate());
                gameBoardGrid.add(pane, fxmlType.getxCordinate(), fxmlType.getyCordinate());
            }
        }*/

     /*   for(int i=0; i < spaceCount; i++){
          //  spaces[i].create();
        }*/

        /*for(Map.Entry<ISpace, SpaceFxmlType> entry : spaces.entrySet()) {
            ISpace space = entry.getKey();
            if(space instanceof Property){
                SpaceFxmlType fxmlType = entry.getValue();
                Pane pane = fxmlType.createSpace(space);
                gameBoardGrid.add(pane,fxmlType.getxCordinate(),fxmlType.getyCordinate());
            }*/


        // do what you have to do here
        // In your case, another loop.

     /*   try{
            System.out.println("Deniyorum Ulan");
        /*    FXMLLoader cardLoader = new FXMLLoader(getClass().getResource("/com.client.controller/Property.fxml"));

            Pane property = cardLoader.load();*/
            /*System.out.println(property.getChildren());
             Text text = (Text) property.getChildren().get(1);
        }catch(IOException ex){
            System.out.println(ex);

        }*/


    }




}



