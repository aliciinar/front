package com.client.controller;

import com.client.controller.spaceCreate.SpaceFxmlType;
import com.client.game.Managers.SpaceManager;
import com.client.game.Spaces.ISpace;
import com.client.game.Spaces.PurchasableSpace.Property;
import com.client.game.Spaces.SpaceCreation.ISpaceCreatorFactory;
import com.client.game.Spaces.SpaceCreation.NormalCreation;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.util.Pair;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

import javafx.event.ActionEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GameBoard {


   @FXML
   GridPane gameBoardGrid;

    @FXML
    ImageView player1;

    @FXML
    private ImageView diceImage1;

    @FXML
    private ImageView diceImage2;

    int lastindex = 0;

    @FXML
    public  void  roll(ActionEvent event){
        Random random = new Random();
        int dice1Val = random.nextInt(6)+1;
        int dice2Val = random.nextInt(6)+1;

        File file1 = new File("src/main/resources/images/Dice" + (dice1Val)+".png");
        File file2 = new File("src/main/resources/images/Dice" + (dice2Val)+".png");
        diceImage1.setImage(new Image(file1.toURI().toString()));
        diceImage2.setImage(new Image(file2.toURI().toString()));
        move(dice1Val + dice2Val);
    }


    public  void  move(int rollValue) {


        double xVal = player1.getX();
        double yVal = player1.getY();
        int rollVal =  lastindex + rollValue;

            while (lastindex < rollValue ) {
                if (lastindex < 4) {

                    yVal -= 140;
                } else if (lastindex < 8) {


                    xVal += 110;

                } else if (lastindex < 12) {
                    yVal += 140;



                } else {
                    xVal -= 110;


                }
                lastindex += 1;
                player1.setX(xVal);
                player1.setY(yVal);
                try{
                    Thread.sleep(500);

                }catch (InterruptedException e){
                    e.printStackTrace();

                }


            }
        }



      /* final  int rollVal = rollValue;
        Thread thread = new Thread(){
            public void run(){
                double xVal = player1.getX();
                double yVal = player1.getY();
                int rollValue =  lastindex + rollVal;
                try {
                    while (lastindex < rollValue ) {
                        if (lastindex < 4) {

                            yVal -= 140;
                        } else if (lastindex < 8) {


                            xVal += 110;

                        } else if (lastindex < 12) {
                            yVal += 140;



                        } else {
                            xVal -= 110;


                        }
                        lastindex += 1;
                        player1.setX(xVal);
                        player1.setY(yVal);
                        Thread.sleep(500);
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();

                }
            }
        };
        System.out.println("Thread başladı");
        thread.start();*/







   @FXML
    public void initialize() {
        player1.setX(50);
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



